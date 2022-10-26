package pl.lotto.resultannouncer;

import pl.lotto.resultannouncer.dto.ResultAnnouncerMessageDto;
import pl.lotto.resultchecker.dto.WinnersDto;

public class ResultAnnouncerFacade {



    public ResultAnnouncerMessageDto checkResults(String hash) {
        WinnersDto winnersDto = new WinnersDto();
        if(ticketDto.getHash().equals(hash)) {
            return ResultAnnouncerMessageDto.CONGRATULATIONS_YOU_WON;
        }
        return ResultAnnouncerMessageDto.NO_HITS_TRY_AGAIN;
    }
}
