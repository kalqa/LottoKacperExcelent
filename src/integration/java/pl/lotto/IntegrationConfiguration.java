package pl.lotto;

import java.time.*;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import pl.lotto.AdjustableClockIntegration;
import pl.lotto.numbergenerator.RandomNumberGenerable;

@Configuration
public class IntegrationConfiguration {

    @Bean
    @Primary
    AdjustableClockIntegration clock(){
        return AdjustableClockIntegration.ofLocalDateAndLocalTime(LocalDate.of(2022, 11,16),LocalTime.of(10,0),ZoneId.systemDefault());
    }
}
