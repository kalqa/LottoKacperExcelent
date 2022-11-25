package pl.lotto.numberreceiver.dto;

public record NumberReceiverResponseDto(
        TicketDto ticketDto,
        String message) {
}
