package pl.lotto.resultannouncer;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ResponseRepositoryTestImpl implements ResponseRepository {

    private final Map<String, Response> responseList = new ConcurrentHashMap<>();
    @Override
    public Response save(Response response) {
        return responseList.put(response.getHash(),response);
    }

    @Override
    public Response findById(String hash) {
        return responseList.get(hash);
    }
}
