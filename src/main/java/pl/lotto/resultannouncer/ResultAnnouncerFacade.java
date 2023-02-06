package pl.lotto.resultannouncer;

import lombok.AllArgsConstructor;
import pl.lotto.resultannouncer.dto.ResponseDto;
import pl.lotto.resultannouncer.dto.ResultAnnouncerResponseDto;
import pl.lotto.resultchecker.ResultCheckerFacade;
import pl.lotto.resultchecker.dto.ResultDto;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static pl.lotto.resultannouncer.MessageResponse.*;

@AllArgsConstructor
public class ResultAnnouncerFacade {


    public static final LocalTime RESULTS_ANNOUNCEMENT_TIME = LocalTime.of(12, 0).plusMinutes(5); //TODO: to do konfiguracji jakiejś
    private final ResultCheckerFacade resultCheckerFacade;
    private final ResponseRepository responseRepository;
    private final Clock clock;


    public ResultAnnouncerResponseDto checkResult(String hash) {
        ResultDto resultDto = resultCheckerFacade.generateResult(hash);
        if(resultDto == null) {
            return new ResultAnnouncerResponseDto(null, HASH_DOES_NOT_EXIST_MESSAGE.info);
        }
//        if (responseRepository.existsById(hash)) {
//            return new ResultAnnouncerResponseDto(null, ALREADY_CHECKED.info);
//        }
        ResponseDto responseDto = buildResponseDto(resultDto);
        responseRepository.save(buildResponse(responseDto));
//        if (responseRepository.existsById(hash) && !isAfterResultAnnouncementTime(resultDto)) {
//            return new ResultAnnouncerResponseDto(responseDto, WAIT_MESSAGE.info);
//        }
        if (resultCheckerFacade.generateResult(hash).isWinner()) {
            return new ResultAnnouncerResponseDto(responseDto, WIN_MESSAGE.info);
        }
        return new ResultAnnouncerResponseDto(responseDto, LOSE_MESSAGE.info);
    }

    private static ResultResponse buildResponse(ResponseDto responseDto) {
        return ResultResponse.builder()
                .hash(responseDto.getHash())
                .numbers(responseDto.getNumbers())
                .hitNumbers(responseDto.getHitNumbers())
                .drawDate(responseDto.getDrawDate())
                .isWinner(responseDto.isWinner())
                .build();
    }

    private static ResponseDto buildResponseDto(ResultDto resultDto) {
        return ResponseDto.builder()
                .hash(resultDto.getHash())
                .numbers(resultDto.getNumbers())
                .hitNumbers(resultDto.getHitNumbers())
                .drawDate(resultDto.getDrawDate())
                .isWinner(resultDto.isWinner())
                .build();
    }

    private boolean isAfterResultAnnouncementTime(ResultDto resultDto) {
        LocalDateTime announcementDateTime = LocalDateTime.of(resultDto.getDrawDate().toLocalDate(), RESULTS_ANNOUNCEMENT_TIME); //
        return LocalDateTime.now(clock).isAfter(announcementDateTime);
    }

}

