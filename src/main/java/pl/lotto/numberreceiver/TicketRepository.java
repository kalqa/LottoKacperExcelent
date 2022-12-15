package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.TicketDto;

import java.time.LocalDateTime;
import java.util.Collection;

public interface TicketRepository {

    Collection<Ticket> findAllTicketsByDrawDate(LocalDateTime drawDate);

    Ticket save(Ticket ticket);

    Ticket findByHash(String hash);
}
