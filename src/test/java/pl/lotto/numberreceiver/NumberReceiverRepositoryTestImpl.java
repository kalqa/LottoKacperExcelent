package pl.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class NumberReceiverRepositoryTestImpl implements NumberReceiverRepository {

    private final Map<String, Ticket> userNumbers = new ConcurrentHashMap<>();

    @Override
    public Ticket save(Ticket ticket) {
        return userNumbers.put(ticket.getHash(), ticket);
    }

    @Override
    public Collection<Ticket> findAllByDrawDate(LocalDateTime drawDate) {
        return userNumbers.values()
                .stream()
                .filter(ticket -> ticket.getDrawDate().isEqual(drawDate))
                .collect(Collectors.toSet());
    }

    @Override
    public boolean exists(String hash) {
        return userNumbers.containsKey(hash);
    }
}
