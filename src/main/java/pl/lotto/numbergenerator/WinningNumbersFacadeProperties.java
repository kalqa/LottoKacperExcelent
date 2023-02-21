package pl.lotto.numbergenerator;

import lombok.Builder;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "lotto.number-generator.facade")
@Builder
public record WinningNumbersFacadeProperties(int maximalWinningNumbers, int lowestNumber, int maxNumber) {
}
