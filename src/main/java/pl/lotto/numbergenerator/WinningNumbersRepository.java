package pl.lotto.numbergenerator;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

public interface WinningNumbersRepository extends MongoRepository<WinningNumbers,String> {

    WinningNumbers save(WinningNumbers winningNumbers);

    Optional<WinningNumbers> findNumbersByDate(LocalDateTime date);
}
