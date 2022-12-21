package pl.lotto.resultchecker;

import pl.lotto.resultchecker.dto.ResultDto;

import java.util.List;
import java.util.stream.Collectors;

class WinnersDtoMapper {

    List<ResultDto> mapPlayersToWinners(List<Player> players) {
        return players.stream()
                .filter(Player::isWinner)
                .map(player -> ResultDto.builder()
                        .hash(player.getHash())
                        .numbers(player.getNumbers())
                        .hitNumbers(player.getHitNumbers())
                        .drawDate(player.getDrawDate())
                        .isWinner(player.isWinner())
                        .build())
                .collect(Collectors.toList());
    }
}