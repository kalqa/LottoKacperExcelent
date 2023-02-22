package pl.lotto.feature;


import com.github.tomakehurst.wiremock.client.WireMock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import pl.lotto.BaseIntegrationTest;
import pl.lotto.numbergenerator.WinningNumbersNotFoundException;
import pl.lotto.numberreceiver.dto.NumberReceiverResponseDto;
import pl.lotto.resultannouncer.dto.ResultAnnouncerResponseDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LottoUserWinnerIntegrationTest extends BaseIntegrationTest {

    @Test
    void happy_path_when_user_played_and_after_few_days_want_to_know_if_won() throws Exception {
        //step 1: external service returns 6 random numbers (1,2,3,4,5,6)
        // given
        wireMockServer.stubFor(WireMock.get("/api/v1.0/random?min=1&max=99&count=6")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                                [1, 2, 3, 4, 5, 6, 82, 82, 83, 83, 86, 57, 10, 81, 53, 93, 50, 54, 31, 88, 15, 43, 79, 32, 43]
                                          """.trim()
                        )));


        //step 2: system generated winning numbers for draw date: 19.11.2022 12:00
        // given
        LocalDateTime ticketDrawDate = LocalDateTime.of(2022, 11, 19, 12, 0, 0);
        // when then
        await().atMost(20, TimeUnit.SECONDS)
                .pollInterval(Duration.ofSeconds(1L))
                .until(() -> {
                    try {
                        return !winningNumbersGeneratorFacade.retrieveWinningNumberByDate(ticketDrawDate).getWinningNumbers().isEmpty();
                    } catch (WinningNumbersNotFoundException e) {
                        return false;
                    }
                });


        //step 3: user made POST /inputNumbers with 6 numbers (1, 2, 3, 4, 5, 6) at 16-11-2022 10:00 and got Ticket (DrawDate:19.11.2022 12:00, Hash: 123, message: “Success”)
        // given && when
        ResultActions perform = mockMvc.perform(post("/inputNumbers")
                .content("""
                        {
                        "inputNumbers" : [1,2,3,4,5,6]
                        }
                        """)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        // then
        MvcResult mvcResult = perform.andExpect(status().isOk()).andReturn();
        String json = mvcResult.getResponse().getContentAsString();
        NumberReceiverResponseDto result = objectMapper.readValue(json, NumberReceiverResponseDto.class);
        assertAll(
                () -> assertThat(result.message()).isEqualTo("SUCCESS"),
                () -> assertThat(result.ticketDto().getDrawDate()).isEqualTo(ticketDrawDate));


        //step 4: user made GET /results/notExistingId and system returned 404(NOT_FOUND) and body with (message: Not found for id: notExistingId and status NOT_FOUND)
        // given
        String ticketHash1 = "notExistingId";

        // when
        ResultActions performGetMethod1 = mockMvc.perform(get("/results/" + ticketHash1));

        // then
        performGetMethod1.andExpect(status().isNotFound())
                .andExpect(content().json(
                        """
                                {
                                "message": "Not found for id: notExistingId",
                                "status": "NOT_FOUND"
                                }
                                """
                )).andReturn();


        //step 5: 3 days and 1 minute passed, and it is 1 minute after draw (19.11.2022 12:01)
        // given && when && then
        clock.plusDaysAndMinutes(3, 1);


        //step 6: system generated result for TicketId: 123 with draw date 19.11.2022 12:00, and saved it with 6 hits
        // given && when && then
        await().atMost(20, TimeUnit.SECONDS)
                .pollInterval(Duration.ofSeconds(1L))
                .until(() -> {
                    try {
                        String hash = result.ticketDto().getHash();
                        return resultCheckerFacade.generateResult(hash).getHash().equals(hash);
                    } catch (RuntimeException e) {
                        return false;
                    }
                });


        //step 7: 3 hours passed, and it is 1 minute after announcement time (19.11.2022 15:01)
        // given && when && then
        clock.plusHours(3);


        //step 8: ser made GET /results/sampleTicketId and system returned 200 (OK)
        // given
        String ticketHash = result.ticketDto().getHash();

        // when
        ResultActions performGetMethod = mockMvc.perform(get("/results/" + ticketHash));

        // then
        MvcResult mvcResultGetMethod = performGetMethod.andExpect(status().isOk()).andReturn();
        String jsonGetMethod = mvcResultGetMethod.getResponse().getContentAsString();
        ResultAnnouncerResponseDto finalResult = objectMapper.readValue(jsonGetMethod, ResultAnnouncerResponseDto.class);
        Set<Integer> hitNumbers = result.ticketDto().getNumbers();
        assertAll(
                () -> assertThat(finalResult.message()).isEqualTo("Congratulations, you won!"),
                () -> assertThat(finalResult.responseDto().getHash()).isEqualTo(ticketHash),
                () -> assertThat(finalResult.responseDto().getHitNumbers()).isEqualTo(hitNumbers));

    }

    private String bodyWithWinningNumbersWithDuplicates() {
        return """
                [1, 2, 3, 4, 5, 6, 82, 82, 83, 83, 86, 57, 10, 81, 53, 93, 50, 54, 31, 88, 15, 43, 79, 32, 43]
                """;
    }
}



