package pl.lotto.numberreceiver;

import java.time.*;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

class DrawDateGenerator {

    private static final LocalTime DRAW_TIME = LocalTime.of(12, 0, 0);
    private static final TemporalAdjuster NEXT_DRAW_DAY = TemporalAdjusters.next(DayOfWeek.SATURDAY);
    private static final Clock SYSTEM_UTC = Clock.systemUTC();
    LocalDate getNextDrawDate() {
        LocalDate currentDate = LocalDate.now(SYSTEM_UTC);
        LocalDateTime currentDateTime = LocalDateTime.now(SYSTEM_UTC);
        LocalDate drawDate = currentDate.with(NEXT_DRAW_DAY);
        currentDateTime.isBefore(LocalDateTime.of(drawDate, DRAW_TIME));

        return drawDate;
    }


}
