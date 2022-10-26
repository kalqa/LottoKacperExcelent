package pl.lotto.numberreceiver;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import pl.lotto.numberreceiver.dto.NumberReceiverResponseDto;
import pl.lotto.numberreceiver.dto.TicketDto;

public class NumberReceiverFacade {


    //TODO: finals to config ??
    public NumberReceiverResponseDto inputNumbers(Set<Integer> numbersFromUser) {
        //TODO:Czy response powinien byc enumem czy lepiej jakas klasa co generuje bardziej tresciwe komunikaty
        NumberValidator numberValidator = new NumberValidator();
        List<ValidationResult> validate = numberValidator.validate(numbersFromUser);
        if (!validate.isEmpty()) {
            String resultMessage = numberValidator.createResultMessage();
            return new NumberReceiverResponseDto(null, resultMessage);
        }
        String hash = UUID.randomUUID().toString();
        LocalDate drawDate = new DrawDateGenerator().getNextDrawDate();
        TicketDto generatedTicket = TicketDto.builder()
                .hash(hash)
                .numbers(numbersFromUser)
                .drawDate(drawDate)
                .build();
        //TODO:save ticket to DB
        return new NumberReceiverResponseDto(null, "dupa");
    }


    //TODO: to tez do jakiegoś configa może ?

}
