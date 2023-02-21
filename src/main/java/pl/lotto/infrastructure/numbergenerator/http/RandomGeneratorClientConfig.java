package pl.lotto.infrastructure.numbergenerator.http;

import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.lotto.numbergenerator.RandomNumberGenerable;
import pl.lotto.numbergenerator.WinningNumbersFacadeProperties;

@Configuration
public class RandomGeneratorClientConfig {

    @Bean
    public RestTemplateResponseErrorHandler restTemplateResponseErrorHandler() {
        return new RestTemplateResponseErrorHandler();
    }

    @Bean
    public RestTemplate restTemplate(@Value("${lotto.number-generator.http.client.config.connectionTimeout:1000}") long connectionTimeout,
                              @Value("${lotto.number-generator.http.client.config.readTimeout:1000}") long readTimeout,
                              RestTemplateResponseErrorHandler restTemplateResponseErrorHandler) {
        return new RestTemplateBuilder()
                .errorHandler(restTemplateResponseErrorHandler)
                .setConnectTimeout(Duration.ofMillis(connectionTimeout))
                .setReadTimeout(Duration.ofMillis(readTimeout))
                .build();
    }

    @Bean
    public RandomNumberGenerable remoteNumberGeneratorClient(RestTemplate restTemplate,
                                                             @Value("${lotto.number-generator.http.client.config.uri:http://example.com}") String uri,
                                                             @Value("${lotto.number-generator.http.client.config.port:5057}") int port,
                                                             WinningNumbersFacadeProperties properties) {
        return new RandomGeneratorRestTemplate(restTemplate, uri, port, properties);
    }
}
