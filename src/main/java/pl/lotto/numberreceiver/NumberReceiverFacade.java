package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.NumberReceiverResponseDto;
import pl.lotto.numberreceiver.dto.TicketDto;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;

public class NumberReceiverFacade {


    //TODO: Extract finals to config ??

    private static final int QUANTITY_OF_NUMBERS_FROM_USER = 6;
    private static final int MAX_VALUE_NUMBER_FROM_USER = 99;
    private static final int MIN_VALUE_NUMBER_FROM_USER = 1;



    public NumberReceiverResponseDto inputNumbers(Set<Integer> numbersFromUser) {

        if (isNumberValid(numbersFromUser)) {
            String hash = UUID.randomUUID().toString();
            LocalDate drawDate = getNextDrawDate();
            // TODO:  set interval of draws
            TicketDto generatedTicket = TicketDto.builder()
                    .hash(hash)
                    .numbers(numbersFromUser)
                    .drawDate(drawDate)
                    .build();
            //TODO:save ticket to DB
            return NumberReceiverResponseDto.INPUT_SUCCESS;
        }
        return NumberReceiverResponseDto.INPUT_ERROR;
    }

    private static boolean isNumberValid(Set<Integer> numbersFromUser) {
        return numbersFromUser.size() == QUANTITY_OF_NUMBERS_FROM_USER && isNumberInRange(numbersFromUser);
    }

    private static boolean isNumberInRange(Set<Integer> numbersFromUser) {

        Integer max = Collections.max(numbersFromUser);
        Integer min = Collections.min(numbersFromUser);

        return min >= MIN_VALUE_NUMBER_FROM_USER && max <= MAX_VALUE_NUMBER_FROM_USER;    }

    private LocalDate getNextDrawDate() {
        LocalDate currentDate = LocalDate.now();
        LocalDateTime currentDayTime = LocalDateTime.now();
        LocalDate drawDate = currentDate.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        LocalTime drawTime = LocalTime.of(12,0,0);
        currentDayTime.isBefore(LocalDateTime.of(drawDate, drawTime));

        return currentDate.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
    }
}
