package pl.lotto.resultannouncer;

import lombok.AllArgsConstructor;
import pl.lotto.resultannouncer.dto.ResponseDto;
import pl.lotto.resultannouncer.dto.ResultAnnouncerResponseDto;
import pl.lotto.resultchecker.ResultCheckerFacade;
import pl.lotto.resultchecker.dto.ResultDto;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static pl.lotto.resultannouncer.MessageResponse.*;

@AllArgsConstructor
public class ResultAnnouncerFacade {


    public static final LocalTime RESULTS_ANNOUNCEMENT_TIME = LocalTime.of(12, 0).plusMinutes(5); //TODO: to do konfiguracji jakiejś
    private final ResultCheckerFacade resultCheckerFacade;
    private final ResponseRepository responseRepository;


    public ResultAnnouncerResponseDto checkResult(String hash) {
        ResultDto resultDto = resultCheckerFacade.generateResult(hash);

        if (responseRepository.findById(hash) == null && resultDto == null) {
            return new ResultAnnouncerResponseDto(null, HASH_DOES_NOT_EXIST_MESSAGE.info);
        }

        ResponseDto responseDto = ResponseDto.builder()
                .hash(resultDto.getHash())
                .numbers(resultDto.getNumbers())
                .hitNumbers(resultDto.getHitNumbers())
                .drawDate(resultDto.getDrawDate())
                .isWinner(resultDto.isWinner())
                .build();

        responseRepository.save(Response.builder()
                .hash(responseDto.getHash())
                .numbers(responseDto.getNumbers())
                .hitNumbers(responseDto.getHitNumbers())
                .drawDate(responseDto.getDrawDate())
                .isWinner(responseDto.isWinner())
                .build());

        if (responseRepository.findById(hash) != null && !isAfterResultAnnouncementTime(resultDto)) {
            return new ResultAnnouncerResponseDto(responseDto, WAIT_MESSAGE.info);
        }
        if (resultCheckerFacade.generateResult(hash).isWinner()) {
            return new ResultAnnouncerResponseDto(responseDto, WIN_MESSAGE.info);
        }

        return new ResultAnnouncerResponseDto(responseDto, LOSE_MESSAGE.info);
    }

    private boolean isAfterResultAnnouncementTime(ResultDto resultDto) {
        LocalDateTime announcementDateTime = LocalDateTime.of(resultDto.getDrawDate().toLocalDate(), RESULTS_ANNOUNCEMENT_TIME); //
        return LocalDateTime.now().isAfter(announcementDateTime);
        //TODO: znalazlem to na necie czy uzyć .now czy Uzależnić to od clocka bo w sumie niczym to się nie różni
//        return TimeConfiguration.currentDateTime().isAfter(announcementDateTime);
    }

}

