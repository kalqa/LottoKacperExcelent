package pl.lotto.resultchecker;

import lombok.AllArgsConstructor;
import pl.lotto.numbergenerator.WinningNumbersGeneratorFacade;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.TicketDto;
import pl.lotto.resultchecker.dto.WinnerDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ResultCheckerFacade {

    WinningNumbersGeneratorFacade generator;
    NumberReceiverFacade receiver;

    public WinnerDto generateResult(String hash) {
        Set<Integer> winningNumbers = generator.generateWinningNumbers().getWinningNumbers();
        Set<Integer> usersNumbers = receiver.getTicketByHash(hash).getNumbers();
        Set<Integer> matchingNumbers = retrieveMatchingNumbers(winningNumbers, usersNumbers);
        return WinnerDto.builder()
                .hash(hash)
                .numbers(matchingNumbers)
                .drawDate(receiver.getTicketByHash(hash).getDrawDate())
                .build();
    }

    private Set<Integer> retrieveMatchingNumbers(Set<Integer> winningNumbers, Set<Integer> usersNumbers) {
        return usersNumbers.stream()
                .filter(winningNumbers::contains)
                .collect(Collectors.toSet());
    }

    public List<WinnerDto> generateWinners() {
        List<TicketDto> allTicketsByDate = receiver.getAllTicketsByDate(LocalDate.now());
        Set<Integer> winningNumbers = generator.generateWinningNumbers().getWinningNumbers();
        return createWinners(allTicketsByDate, winningNumbers);
    }

    private List<WinnerDto> createWinners(List<TicketDto> allTicketsByDate, Set<Integer> winningNumbers) {
        return allTicketsByDate.stream()
                .filter(ticketDto -> ticketDto.getNumbers().containsAll(winningNumbers))
                .map(ticketDto -> WinnerDto.builder()
                        .hash(ticketDto.getHash())
                        .numbers(ticketDto.getNumbers())
                        .drawDate(ticketDto.getDrawDate())
                        .build())
                .toList();
    }
}
