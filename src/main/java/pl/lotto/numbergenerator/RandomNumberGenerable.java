package pl.lotto.numbergenerator;

import pl.lotto.numbergenerator.dto.SixRandomNumbersDto;

public interface RandomNumberGenerable {

    SixRandomNumbersDto generateSixRandomNumbers(int count, int lowerBand, int upperBand);
}

