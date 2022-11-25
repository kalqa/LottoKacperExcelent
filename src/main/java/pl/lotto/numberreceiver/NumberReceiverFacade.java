package pl.lotto.numberreceiver;

import lombok.AllArgsConstructor;
import pl.lotto.numberreceiver.dto.NumberReceiverResponseDto;
import pl.lotto.numberreceiver.dto.TicketDto;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static pl.lotto.numberreceiver.ValidationResult.INPUT_SUCCESS;


public class NumberReceiverFacade {

    private final NumberValidator numberValidator;
    private final DrawDateGenerator drawDateGenerator;
    private final HashMap<String, Ticket> userNumbers = new HashMap<>();
    HashGenerable hashGenerator;

    public NumberReceiverFacade(NumberValidator numberValidator, DrawDateGenerator drawDateGenerator, HashGenerable hashGenerator) {
        this.numberValidator = numberValidator;
        this.drawDateGenerator = drawDateGenerator;
        this.hashGenerator = hashGenerator;
    }

    public NumberReceiverResponseDto inputNumbers(Set<Integer> numbersFromUser) {
        List<ValidationResult> validationResultList = numberValidator.validate(numbersFromUser);
        if (!validationResultList.isEmpty()) {
            String resultMessage = numberValidator.createResultMessage();
            return new NumberReceiverResponseDto(null, resultMessage);
        }
        LocalDate drawDate = drawDateGenerator.getNextDrawDate();

        String hash = hashGenerator.getHash();

        TicketDto generatedTicket = TicketDto.builder()
                .hash(hash)
                .numbers(numbersFromUser)
                .drawDate(drawDate)
                .build();

        Ticket savedTicket = Ticket.builder()
                .hash(hash)
                .numbers(generatedTicket.getNumbers())
                .drawDate(generatedTicket.getDrawDate())
                .build();

        userNumbers.put(hash, savedTicket);

        return new NumberReceiverResponseDto(generatedTicket, INPUT_SUCCESS.info);
    }

    public TicketDto getTicketByHash(String hash) {
        if (!userNumbers.containsKey(hash)) {
            return null;
        }
        Ticket ticket = userNumbers.get(hash);
        return TicketDto.builder()
                .hash(ticket.getHash())
                .numbers(ticket.getNumbers())
                .drawDate(ticket.getDrawDate())
                .build();

    }

    public List<TicketDto> getAllTicketsByDate(LocalDate date) {
        LocalDate nextDrawDate = drawDateGenerator.getNextDrawDate();
        if(date.isBefore(nextDrawDate) || date.isAfter(nextDrawDate)) {
            return Collections.emptyList();
        }
        return userNumbers.values()
                .stream()
                .filter(ticket -> ticket.getDrawDate().isEqual(date))
                .map(ticket -> TicketDto.builder()
                        .hash(ticket.getHash())
                        .numbers(ticket.getNumbers())
                        .drawDate(ticket.getDrawDate())
                        .build())
                .collect(Collectors.toList());
    }
}

