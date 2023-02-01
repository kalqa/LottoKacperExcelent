package pl.lotto.numberreceiver;

import org.springframework.data.mongodb.repository.*;
import org.springframework.stereotype.*;

import java.time.LocalDateTime;
import java.util.Collection;

@Repository
public interface TicketRepository extends MongoRepository<Ticket, String> {

    Collection<Ticket> findAllTicketsByDrawDate(LocalDateTime drawDate);

    Ticket findByHash(String hash);
}
