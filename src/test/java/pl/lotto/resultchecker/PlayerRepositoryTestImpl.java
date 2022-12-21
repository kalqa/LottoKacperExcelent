package pl.lotto.resultchecker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class PlayerRepositoryTestImpl implements PlayerRepository {

    private final Map<String, Player> playersList = new ConcurrentHashMap<>();


    @Override
    public void saveAll(List<Player> players) {
        players.stream()
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
