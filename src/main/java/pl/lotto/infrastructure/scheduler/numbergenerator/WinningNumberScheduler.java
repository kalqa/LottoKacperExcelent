package pl.lotto.infrastructure.scheduler.numbergenerator;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.lotto.numbergenerator.WinningNumbersGeneratorFacade;

@Data
@Component
@Slf4j
public class WinningNumberScheduler {

    private final WinningNumbersGeneratorFacade winningNumbersGeneratorFacade;

    @Scheduled(cron= "${lotto.numberGenerator.lotteryRunOccurrence}")
    public void generateNumbers() {
        log.info("WinningNumbersScheduler started...");
        winningNumbersGeneratorFacade.generateWinningNumbers();
    }
}
