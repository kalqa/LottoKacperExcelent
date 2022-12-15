package pl.lotto.resultchecker;

import pl.lotto.resultchecker.dto.WinnerDto;

import java.util.List;

public interface WinnersRepository {


    void saveAll(List<WinnerDto> winnersDto);
}
