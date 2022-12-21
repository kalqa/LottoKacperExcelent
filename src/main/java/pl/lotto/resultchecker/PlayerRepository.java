package pl.lotto.resultchecker;

import java.util.List;

public interface PlayerRepository {


    void saveAll(List<Player> players);

    List<Player> findAll();

    Player findById(String hash);
}
