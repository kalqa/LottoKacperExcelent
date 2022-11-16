package pl.lotto.resultchecker;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import pl.lotto.numbergenerator.WinningNumbersGeneratorFacade;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.TicketDto;
import pl.lotto.resultchecker.dto.ResultTicketDto;
import pl.lotto.resultchecker.dto.WinnersDto;

public class ResultCheckerFacade {

    NumberReceiverFacade receiver;
    WinningNumbersGeneratorFacade generator;

    ResultCheckerFacade(NumberReceiverFacade receiver, WinningNumbersGeneratorFacade generator) {
        this.receiver = receiver;
        this.generator = generator;
    }

    //TODO: Czy program sprawdza tylko zwyciezcow, czy tez ile sie trafilo liczb
    public ResultTicketDto generateResult(TicketDto ticketDto, Set<Integer> winningNumbers) {
        receiver.userNumbers(LocalDateTime.now());

        Set<Integer> matchingNumbers = new HashSet<>();
        setMatchingNumbers(ticketDto.getNumbers(), winningNumbers, matchingNumbers);

        ResultTicketDto resultTicket = ResultTicketDto.builder()
                .hash(ticketDto.getHash())
                .numbers(matchingNumbers)
                .drawDate(ticketDto.getDrawDate())
                .build();
        //TODO: do osobnej metody ?
        ArrayList<ResultTicketDto> winningTickets = new ArrayList<>();
        //tutaj ifa ze sprawdzeniem czy moje liczby sa wygrane, jak tak to dodac do winners
        winningTickets.add(resultTicket);
        WinnersDto winners = WinnersDto.builder()
                .winners(winningTickets)
                .build();

        return resultTicket;
    }

    private static void setMatchingNumbers(Set<Integer> inputNumbers, Set<Integer> winningNumbers, Set<Integer> matchingNumbers) {
        for (Integer number : winningNumbers) {
            if (inputNumbers.contains(number)) {
                matchingNumbers.add(number);
            }
        }
    }
}
