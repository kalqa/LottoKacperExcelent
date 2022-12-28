package pl.lotto.resultchecker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerRepositoryTestImpl implements PlayerRepository {

    private final Map<String, Player> playersList = new ConcurrentHashMap<>();


    @Override
    public List<Player> saveAll(List<Player> players) {
        return players.stream()
                .map(player -> playersList.put(player.getHash(), player))
                .toList();

    }

    @Override
    public List<Player> findAll() {
        return new ArrayList<>(playersList.values());
    }

    @Override
    public Player findById(String hash) {
        return playersList.get(hash);
    }
}
