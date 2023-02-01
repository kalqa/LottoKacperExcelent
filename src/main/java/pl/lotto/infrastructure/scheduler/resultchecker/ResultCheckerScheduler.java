package pl.lotto.infrastructure.scheduler.resultchecker;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.lotto.resultchecker.ResultCheckerFacade;

@Data
@Component
@Slf4j
public class ResultCheckerScheduler {

    private final ResultCheckerFacade resultCheckerFacade;

    //@Scheduled(cron= "${lotto.resultChecker.lotteryRunOccurrence}")
    public void generateWinners() {
        log.info("ResultCheckerScheduler started...");
        resultCheckerFacade.generateWinners();
    }
}
