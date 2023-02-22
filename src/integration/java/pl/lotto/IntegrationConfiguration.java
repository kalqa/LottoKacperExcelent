package pl.lotto;

import java.time.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class IntegrationConfiguration {

    @Bean
    @Primary
    AdjustableClock clock(){
        return AdjustableClock.ofLocalDateAndLocalTime(LocalDate.of(2022, 11,16),LocalTime.of(10,0),ZoneId.systemDefault());
    }
}
