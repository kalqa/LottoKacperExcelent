package pl.lotto.infrastructure.scheduler.resultchecker;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.lotto.numbergenerator.WinningNumbersGeneratorFacade;
import pl.lotto.resultchecker.ResultCheckerFacade;
import pl.lotto.resultchecker.dto.PlayersDto;

@Data
@Component
@Slf4j
public class ResultCheckerScheduler {

    private final ResultCheckerFacade resultCheckerFacade;
    private final WinningNumbersGeneratorFacade winningNumbersGeneratorFacade;

    @Scheduled(cron = "${lotto.resultChecker.lotteryRunOccurrence}")
    public PlayersDto generateWinners() {
        log.info("ResultCheckerScheduler started...");
        if (!winningNumbersGeneratorFacade.areWinningNumbersGeneratedByDate()) {
            log.info("Winning numbers are not generated");
            throw new RuntimeException("Winning numbers are not generated");
        }
        log.info("Winning numbers has been fetched");
        return resultCheckerFacade.generateWinners();


    }
}
