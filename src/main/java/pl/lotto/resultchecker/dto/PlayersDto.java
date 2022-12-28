package pl.lotto.resultchecker.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class PlayersDto {
    private List<ResultDto> results;
    private String message;
}
