package pl.lotto.resultannouncer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ResponseRepositoryTestImpl implements ResponseRepository {

    private final Map<String, ResultResponse> responseList = new ConcurrentHashMap<>();
    @Override
    public ResultResponse save(ResultResponse resultResponse) {
        return responseList.put(resultResponse.getHash(), resultResponse);
    }

    @Override
    public ResultResponse findById(String hash) {
        return responseList.get(hash);
    }

    @Override
    public boolean exists(String hash) {
        return responseList.containsKey(hash);
    }
}
