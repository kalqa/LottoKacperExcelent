package pl.lotto.feature;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class IntegrationConfiguration {

    @Bean
    @Primary
    Clock clock(){
        return Clock.fixed(LocalDateTime.of(2022, 11, 16, 10, 0, 0).toInstant(ZoneOffset.UTC), ZoneId.of("Europe/London"));
    }
}
