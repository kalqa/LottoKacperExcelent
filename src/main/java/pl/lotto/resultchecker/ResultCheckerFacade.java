package pl.lotto.resultchecker;

import lombok.AllArgsConstructor;
import pl.lotto.numbergenerator.WinningNumbersGeneratorFacade;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.TicketDto;
import pl.lotto.resultchecker.dto.WinnerDto;
import pl.lotto.resultchecker.dto.WinnersDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ResultCheckerFacade {

    WinningNumbersGeneratorFacade generator;
    NumberReceiverFacade receiver;
//    WinnersRepository winnersRepository;

    public WinnersDto generateWinners() {
        LocalDateTime drawDate = receiver.nextDrawDate();
        List<TicketDto> allTicketsByDate = receiver.getAllTicketsByDate(drawDate);
        Set<Integer> winningNumbers = generator.generateWinningNumbers().getWinningNumbers();

        List<WinnerDto> winnersDto = retrieveWinnersList(allTicketsByDate, winningNumbers);
//        winnersRepository.saveAll(winnersDto);

        return WinnersDto.builder()
                .winners(winnersDto)
                .build();
    }

    private static List<WinnerDto> retrieveWinnersList(List<TicketDto> allTicketsByDate, Set<Integer> winningNumbers) {
        List<WinnerDto> winnersDto = allTicketsByDate.stream()
                .filter(ticketDto -> ticketDto.getNumbers().containsAll(winningNumbers))
                .map(ticketDto -> WinnerDto.builder()
                        .hash(ticketDto.getHash())
                        .numbers(ticketDto.getNumbers())
                        .drawDate(ticketDto.getDrawDate())
                        .build())
                .collect(Collectors.toList());
        return winnersDto;
    }

    public WinnerDto generateResult(String hash) {
        TicketDto ticketDto = receiver.findByHash(hash);
        Set<Integer> winningNumbers = generator.generateWinningNumbers().getWinningNumbers();
        Set<Integer> matchingNumbers = ticketDto.getNumbers().stream().
                filter(winningNumbers::contains)
                .collect(Collectors.toSet());

        return WinnerDto.builder()
                .hash(hash)
                .numbers(matchingNumbers)
                .drawDate(ticketDto.getDrawDate())
                .build();

        //TODO: Nie ogarniam czy to tak ma wyglądać

    }

}
