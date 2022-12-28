package pl.lotto.resultchecker;

import pl.lotto.numbergenerator.WinningNumbersGeneratorFacade;
import pl.lotto.numberreceiver.NumberReceiverFacade;

public class ResultCheckerConfiguration {


    ResultCheckerFacade createForTest(WinningNumbersGeneratorFacade generatorFacade, NumberReceiverFacade receiverFacade, PlayerRepository playerRepository) {
        WinnersRetriever winnerGenerator = new WinnersRetriever(playerRepository);
        return new ResultCheckerFacade(generatorFacade, receiverFacade, playerRepository, winnerGenerator);
    }
}
