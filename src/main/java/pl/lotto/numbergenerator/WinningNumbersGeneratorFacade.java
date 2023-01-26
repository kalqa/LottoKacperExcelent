package pl.lotto.numbergenerator;

import lombok.AllArgsConstructor;
import pl.lotto.numbergenerator.dto.WinningNumbersDto;
import pl.lotto.numberreceiver.NumberReceiverFacade;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
public class WinningNumbersGeneratorFacade {

    RandomNumberGenerable winningNumberGenerator;
    WinningNumberValidator winningNumberValidator;
    WinningNumbersRepository winningNumbersRepository;
    NumberReceiverFacade numberReceiverFacade;

    public WinningNumbersDto generateWinningNumbers() {
        LocalDateTime nextDrawDate = numberReceiverFacade.retrieveNextDrawDate();
        Set<Integer> winningNumbers = winningNumberGenerator.generateSixRandomNumbers();
        winningNumberValidator.validate(winningNumbers);
        winningNumbersRepository.save(WinningNumbers.builder()
                .winningNumbers(winningNumbers)
                .date(nextDrawDate)
                .build());
        return WinningNumbersDto.builder()
                .winningNumbers(winningNumbers)
                .build();
    }

    public WinningNumbersDto retrieveWinningNumberByDate(LocalDateTime date) {
        WinningNumbers numbersByDate = winningNumbersRepository.findNumbersByDate(date)
                .orElseThrow(() -> new RuntimeException("Not Found"));
        return WinningNumbersDto.builder()
                .winningNumbers(numbersByDate.getWinningNumbers())
                .date(numbersByDate.getDate())
                .build();
    }
}
