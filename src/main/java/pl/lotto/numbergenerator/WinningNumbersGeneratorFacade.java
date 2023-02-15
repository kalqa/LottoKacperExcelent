package pl.lotto.numbergenerator;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.lotto.numbergenerator.dto.WinningNumbersDto;
import pl.lotto.numberreceiver.NumberReceiverFacade;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Slf4j
public class WinningNumbersGeneratorFacade {

    RandomNumberGenerable winningNumberGenerator;
    WinningNumberValidator winningNumberValidator;
    WinningNumbersRepository winningNumbersRepository;
    NumberReceiverFacade numberReceiverFacade;

    public WinningNumbersDto generateWinningNumbers() {
        log.info("generateWinningNumbers started");
        LocalDateTime nextDrawDate = numberReceiverFacade.retrieveNextDrawDate();
        Optional<WinningNumbers> numbersByDate = winningNumbersRepository.findNumbersByDate(nextDrawDate);
        if(numbersByDate.isPresent()) {
            log.info("numbers are already generated");
            return WinningNumbersDto.builder()
                    .winningNumbers(numbersByDate.get().getWinningNumbers())
                    .build();
        }

        Set<Integer> winningNumbers = winningNumberGenerator.generateSixRandomNumbers();
        log.info(Arrays.toString(winningNumbers.toArray()));
        winningNumberValidator.validate(winningNumbers);
        winningNumbersRepository.save(WinningNumbers.builder()
                .winningNumbers(winningNumbers)
                .date(nextDrawDate)
                .build());
        log.info("generateWinningNumbers finished");
        return WinningNumbersDto.builder()
                .winningNumbers(winningNumbers)
                .build();
    }

    public WinningNumbersDto retrieveWinningNumberByDate(LocalDateTime date) {
        log.info("retrieveWinningNumberByDate started");
        WinningNumbers numbersByDate = winningNumbersRepository.findNumbersByDate(date)
                .orElseThrow(() -> new RuntimeException("Not Found"));
        log.info("retrieveWinningNumberByDate finished");
        return WinningNumbersDto.builder()
                .winningNumbers(numbersByDate.getWinningNumbers())
                .date(numbersByDate.getDate())
                .build();
    }

    public boolean areWinningNumbersGeneratedByDate(){
        LocalDateTime nextDrawDate = numberReceiverFacade.retrieveNextDrawDate();
        return winningNumbersRepository.existsByDate(nextDrawDate);
    }
}
