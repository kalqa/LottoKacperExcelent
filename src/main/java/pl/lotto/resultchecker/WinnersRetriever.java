package pl.lotto.resultchecker;

import lombok.AllArgsConstructor;
import pl.lotto.numberreceiver.dto.TicketDto;
import pl.lotto.resultchecker.Player.PlayerBuilder;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
class WinnersRetriever {
    private final int NUMBERS_WHEN_PLAYER_WON = 3;

    List<Player> retrieveWinners(List<TicketDto> allTicketsByDate, Set<Integer> winningNumbers) {
        List<Player> players = establishWinners(allTicketsByDate, winningNumbers);
        return players;

    }

    private List<Player> establishWinners(List<TicketDto> allTicketsByDate, Set<Integer> winningNumbers) {
        return allTicketsByDate.stream()
                .map(ticket -> {
                    Set<Integer> hitNumbers = calculateHits(winningNumbers, ticket);
                    return buildPlayer(ticket, hitNumbers);
                })
                .toList();
    }

    private Set<Integer> calculateHits(Set<Integer> winningNumbers, TicketDto ticket) {
        return ticket.getNumbers().stream()
                .filter(winningNumbers::contains)
                .collect(Collectors.toSet());
    }

    private Player buildPlayer(TicketDto ticket, Set<Integer> hitNumbers) {
        PlayerBuilder builder = Player.builder();
        if (isWinner(hitNumbers)) {
            builder.isWinner(true);
        }
        return builder
                .hash(ticket.getHash())
                .numbers(ticket.getNumbers())
                .hitNumbers(hitNumbers)
                .drawDate(ticket.getDrawDate())
                .build();
    }

    private boolean isWinner(Set<Integer> hitNumbers) {
        return hitNumbers.size() >= NUMBERS_WHEN_PLAYER_WON;
    }
}
