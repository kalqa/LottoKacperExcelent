package pl.lotto.numberreceiver;

import org.springframework.context.annotation.*;

import java.time.Clock;

@Configuration
public class NumberReceiverConfiguration {

    @Bean
    Clock clock(){
        return Clock.systemUTC();
    }

    @Bean
    HashGenerable hashGenerable(){
        return new HashGenerator();
    }

    @Bean
    NumberReceiverFacade numberReceiverFacade(HashGenerable hashGenerator, Clock clock, TicketRepository ticketRepository) {
        NumberValidator numberValidator = new NumberValidator();
        DrawDateGenerator drawDateGenerator = new DrawDateGenerator(clock);
        return new NumberReceiverFacade(numberValidator, drawDateGenerator, hashGenerator, ticketRepository);
    }

    NumberReceiverFacade createForTest(HashGenerable hashGenerator, Clock clock, TicketRepository ticketRepository) {
        return numberReceiverFacade(hashGenerator,clock,ticketRepository);
    }


}
