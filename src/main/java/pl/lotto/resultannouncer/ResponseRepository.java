package pl.lotto.resultannouncer;

public interface ResponseRepository {

    ResultResponse save(ResultResponse resultResponse);
    ResultResponse findById(String hash);
    boolean exists(String hash);

}
