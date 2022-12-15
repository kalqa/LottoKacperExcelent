package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.TicketDto;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class TicketRepositoryTestImpl implements TicketRepository {

    private final Map<String, Ticket> tickets = new ConcurrentHashMap<>();
    @Override
    public Collection<Ticket> findAllTicketsByDrawDate(LocalDateTime drawDate) {
        return tickets.values()
                .stream()
                .filter(ticket -> ticket.getDrawDate().isEqual(drawDate))
                .collect(Collectors.toList());
    }

    @Override
    public Ticket save(Ticket ticket) {
        return tickets.put(ticket.getHash(), ticket);
    }

    @Override
    public Ticket findByHash(String hash) {
        return tickets.get(hash);
    }

}
