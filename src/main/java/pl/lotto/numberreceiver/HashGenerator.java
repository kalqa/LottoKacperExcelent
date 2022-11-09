package pl.lotto.numberreceiver;

import java.util.UUID;

public class HashGenerator {

    String getHash() {
        return UUID.randomUUID().toString();
    }
}
