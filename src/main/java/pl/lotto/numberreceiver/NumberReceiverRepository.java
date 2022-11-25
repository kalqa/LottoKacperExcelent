package pl.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.util.Collection;

public interface NumberReceiverRepository {

    Ticket save(Ticket ticket);

    Collection<Ticket> findAllByDrawDate(LocalDateTime drawDate);

    boolean exists(String hash);
}
