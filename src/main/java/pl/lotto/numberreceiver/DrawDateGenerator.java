package pl.lotto.numberreceiver;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

class DrawDateGenerator {

    private static final LocalTime DRAW_TIME = LocalTime.of(12, 0, 0);
    private static final TemporalAdjuster NEXT_DRAW_DAY = TemporalAdjusters.next(DayOfWeek.SATURDAY);

//    Clock clock;
    LocalDate getNextDrawDate() {
//        LocalDateTime.now(clock);

        LocalDate currentDate = LocalDate.now();
        LocalDateTime currentDayTime = LocalDateTime.now();
        LocalDate drawDate = currentDate.with(NEXT_DRAW_DAY);
        currentDayTime.isBefore(LocalDateTime.of(drawDate, DRAW_TIME));

        return drawDate;
    }
}
