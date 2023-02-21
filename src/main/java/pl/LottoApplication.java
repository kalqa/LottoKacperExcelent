package pl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import pl.lotto.numbergenerator.WinningNumbersFacadeProperties;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties({WinningNumbersFacadeProperties.class})
public class LottoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LottoApplication.class, args);
    }
}

