package pl.lotto.numberreceiver;

import java.time.LocalDate;
import java.util.Set;

class Ticket {

    private String hash;
    private Set<Integer> numbers;
    private LocalDate drawDate;

    Ticket(String hash, Set<Integer> numbers, LocalDate drawDate) {
        this.hash = hash;
        this.numbers = numbers;
        this.drawDate = drawDate;
    }
}
