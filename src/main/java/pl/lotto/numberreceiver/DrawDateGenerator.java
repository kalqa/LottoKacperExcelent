package pl.lotto.numberreceiver;

import java.time.*;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;


class DrawDateGenerator {

    private static final LocalTime DRAW_TIME = LocalTime.of(12, 0, 0);
    private static final TemporalAdjuster NEXT_DRAW_DAY = TemporalAdjusters.next(DayOfWeek.SATURDAY);
    private final Clock clock;

    public DrawDateGenerator(Clock clock) {
        this.clock = clock;
    }

    public LocalDate getNextDrawDate() {
        LocalDate currentDate = LocalDate.now(clock);
        LocalTime currentTime = LocalTime.now(clock);
        LocalDateTime currentDateTime = LocalDateTime.now(clock);
        LocalDate drawDate = currentDate.with(NEXT_DRAW_DAY);
        if (isSaturdayAndBeforeNoon(currentTime, currentDateTime)) {
            return currentDate;
        }
        return drawDate;
    }

    private static boolean isSaturdayAndBeforeNoon(LocalTime currentTime, LocalDateTime currentDateTime) {
        return currentDateTime.getDayOfWeek().equals(DayOfWeek.SATURDAY) && currentTime.isBefore(DRAW_TIME);
    }


}
