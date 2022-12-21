package pl.lotto.resultchecker;

import lombok.AllArgsConstructor;
import pl.lotto.numbergenerator.WinningNumbersGeneratorFacade;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.TicketDto;
import pl.lotto.resultchecker.dto.ResultDto;
import pl.lotto.resultchecker.dto.WinnersDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
public class ResultCheckerFacade {

    WinningNumbersGeneratorFacade generator;
    NumberReceiverFacade receiver;
    PlayerRepository playerRepository;
    WinnersRetriever winnerGenerator;


    public WinnersDto generateWinners() {
        LocalDateTime drawDate = receiver.nextDrawDate();
        List<TicketDto> allTicketsByDate = receiver.getAllTicketsByDate(drawDate);
        Set<Integer> winningNumbers = generator.generateWinningNumbers().getWinningNumbers();
        List<ResultDto> winnersDto = winnerGenerator.retrieveWinners(allTicketsByDate, winningNumbers);
        return WinnersDto.builder()
                .winners(winnersDto)
                .build();
    }

//    private List<WinnerDto> retrieveWinners(List<TicketDto> allTicketsByDate, Set<Integer> winningNumbers) {
//        List<Player> players = establishWinners(allTicketsByDate, winningNumbers);
//        playerRepository.saveAll(players);
//        return mapPlayersToWinners(players);
//
//    }
//    private List<Player> establishWinners(List<TicketDto> allTicketsByDate, Set<Integer> winningNumbers) {
//        List<Player> players = allTicketsByDate.stream()
//                .map(ticket -> {
//                    Set<Integer> hitNumbers = ticket.getNumbers().stream()
//                            .filter(winningNumbers::contains)
//                            .collect(Collectors.toSet());
//                    return Player.builder()
//                            .hash(ticket.getHash())
//                            .numbers(ticket.getNumbers())
//                            .hitNumbers(hitNumbers)
//                            .drawDate(ticket.getDrawDate())
//                            .isWinner(false)
//                            .build();
//                })
//                .toList();
//        players.forEach(player -> player.isWinner(player.getHitNumbers()));
//        return players;
//    }
//
//    private List<WinnerDto> mapPlayersToWinners(List<Player> players) {
//        return players.stream()
//                .filter(Player::isWinner)
//                .map(player -> WinnerDto.builder()
//                        .hash(player.getHash())
//                        .numbers(player.getNumbers())
//                        .hitNumbers(player.getHitNumbers())
//                        .drawDate(player.getDrawDate())
//                        .isWinner(player.isWinner())
//                        .build())
//                .collect(Collectors.toList());
//    }
    //todo:która wersja jest lepsza ? to czy to wydzielone do klas ? i czy dobrze są te klasy porobione


    public ResultDto generateResult(String hash) {
        Player player = playerRepository.findById(hash);
        return ResultDto.builder()
                .hash(hash)
                .numbers(player.getNumbers())
                .hitNumbers(player.getHitNumbers())
                .drawDate(player.getDrawDate())
                .isWinner(player.isWinner())
                .build();
    }
}
