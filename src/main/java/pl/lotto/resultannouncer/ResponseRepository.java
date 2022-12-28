package pl.lotto.resultannouncer;

import java.util.Optional;

public interface ResponseRepository {

    Response save(Response response);
    Response findById(String hash);

}
