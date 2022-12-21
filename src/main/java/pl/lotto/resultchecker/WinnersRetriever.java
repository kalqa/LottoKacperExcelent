package pl.lotto.resultchecker;

import pl.lotto.numberreceiver.dto.TicketDto;
import pl.lotto.resultchecker.dto.ResultDto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


record WinnersRetriever(
        PlayerRepository playerRepository,
        WinnersDtoMapper winnersDtoMapper) {

    List<ResultDto> retrieveWinners(List<TicketDto> allTicketsByDate, Set<Integer> winningNumbers) {
        List<Player> players = establishWinners(allTicketsByDate, winningNumbers);
        playerRepository.saveAll(players);
        return winnersDtoMapper.mapPlayersToWinners(players);

    }

    private List<Player> establishWinners(List<TicketDto> allTicketsByDate, Set<Integer> winningNumbers) {
        List<Player> players = allTicketsByDate.stream()
                .map(ticket -> {
                    Set<Integer> hitNumbers = ticket.getNumbers().stream()
                            .filter(winningNumbers::contains)
                            .collect(Collectors.toSet());
                    return Player.builder()
                            .hash(ticket.getHash())
                            .numbers(ticket.getNumbers())
                            .hitNumbers(hitNumbers)
                            .drawDate(ticket.getDrawDate())
                            .isWinner(false)
                            .build();
                })
                .toList();
        players.forEach(player -> player.isWinner(player.getHitNumbers()));
        return players;
    }
}
