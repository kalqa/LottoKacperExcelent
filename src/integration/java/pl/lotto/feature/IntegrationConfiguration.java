package pl.lotto.feature;

import java.time.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import pl.lotto.AdjustableClockIntegration;

@Configuration
public class IntegrationConfiguration {

    @Bean
    @Primary
    AdjustableClockIntegration clock(){
        return AdjustableClockIntegration.ofLocalDateAndLocalTime(LocalDate.of(2022, 11,16),LocalTime.of(10,0),ZoneId.systemDefault());
    }
}
