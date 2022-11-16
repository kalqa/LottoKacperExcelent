package pl.lotto.numberreceiver;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import pl.lotto.numberreceiver.dto.NumberReceiverResponseDto;
import pl.lotto.numberreceiver.dto.TicketDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static pl.lotto.numberreceiver.ValidationResult.INPUT_SUCCESS;

@AllArgsConstructor
public class NumberReceiverFacade {

    private final NumberValidator numberValidator;
    private final DrawDateGenerator drawDateGenerator;
    HashGenerable hashGenerator;

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
        Ticket ticket = new Ticket(hash, numbersFromUser, drawDate);
        //TODO:save ticket to DB
        return new NumberReceiverResponseDto(generatedTicket, INPUT_SUCCESS.info);
    }

    public void userNumbers(LocalDateTime date){
        return;
    }
}

