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

    public LocalDateTime getNextDrawDate() {
        LocalDateTime currentDateTime = LocalDateTime.now(clock);
        LocalDateTime drawDate = currentDateTime.with(NEXT_DRAW_DAY);
        if (isSaturdayAndBeforeNoon(currentDateTime)) {
            return LocalDateTime.of(currentDateTime.toLocalDate(),DRAW_TIME);
        }
        return LocalDateTime.of(drawDate.toLocalDate(),DRAW_TIME);
    }

    private boolean isSaturdayAndBeforeNoon(LocalDateTime currentDateTime) {
        return currentDateTime.getDayOfWeek().equals(DayOfWeek.SATURDAY) && currentDateTime.toLocalTime().isBefore(DRAW_TIME);
    }


    //TODO: to miało zostać wsadzone do bazy jakoś

}
