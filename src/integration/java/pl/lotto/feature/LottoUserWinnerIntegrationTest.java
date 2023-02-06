package pl.lotto.feature;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import pl.LottoApplication;
import pl.lotto.AdjustableClockIntegration;
import pl.lotto.numbergenerator.WinningNumbersGeneratorFacade;
import pl.lotto.numberreceiver.dto.NumberReceiverResponseDto;
import pl.lotto.resultannouncer.ResultAnnouncerFacade;
import pl.lotto.resultannouncer.dto.ResultAnnouncerResponseDto;
import pl.lotto.resultchecker.ResultCheckerFacade;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {LottoApplication.class, IntegrationConfiguration.class})
@AutoConfigureMockMvc
@Testcontainers
@ActiveProfiles("integration")
public class LottoUserWinnerIntegrationTest {

    @Autowired
    WinningNumbersGeneratorFacade winningNumbersGeneratorFacade;

    @Autowired
    ResultCheckerFacade resultCheckerFacade;

    @Autowired
    ResultAnnouncerFacade resultAnnouncerFacade;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    AdjustableClockIntegration clock;

    @Container
    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"));

    @DynamicPropertySource
    private static void propertyOverride(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Test
    void happy_path_when_user_played_and_after_few_days_want_to_know_if_won() throws Exception {
        //step 1: user send POST to /inputNumbers with numbers (1,2,3,4,5,6)
        //Given
        //When
        ResultActions perform = mockMvc.perform(post("/inputNumbers")
                .content("""
                        {
                        "inputNumbers" : [1,2,3,4,5,6]
                        }
                        """)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        //Then
        MvcResult mvcResult = perform.andExpect(status().isOk()).andReturn();

        String json = mvcResult.getResponse().getContentAsString();
        NumberReceiverResponseDto result = objectMapper.readValue(json, NumberReceiverResponseDto.class);

        LocalDateTime ticketDrawDate = LocalDateTime.of(2022, 11, 19, 12, 0, 0);
        assertAll(
                () -> assertThat(result.message()).isEqualTo("SUCCESS"),
                () -> assertThat(result.ticketDto().getDrawDate()).isEqualTo(ticketDrawDate));

        //step 2: winning numbers should have been generated
        await().atMost(20, TimeUnit.SECONDS)
                .pollInterval(Duration.ofSeconds(1L))
                .until(() -> {
                    try {
                        return !winningNumbersGeneratorFacade.retrieveWinningNumberByDate(ticketDrawDate).getWinningNumbers().isEmpty();
                    } catch (RuntimeException e) {
                        return false;
                    }
                });


        //step 3: clock is adjusted to 1 minute after draw and results for user should be generated
        clock.plusDaysAndMinutes(3,1);
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

        //step 4: user send GET /results/id expected message : Congratulations, you won!

        String ticketHash = result.ticketDto().getHash();
        ResultActions performGetMethod = mockMvc.perform(get("/results/id")
                .param("id", ticketHash)
        );

        MvcResult mvcResultGetMethod = performGetMethod.andExpect(status().isOk()).andReturn();

        String jsonGetMethod = mvcResultGetMethod.getResponse().getContentAsString();
        ResultAnnouncerResponseDto finalResult = objectMapper.readValue(jsonGetMethod, ResultAnnouncerResponseDto.class);

        assertAll(
                () -> assertThat(finalResult.message()).isEqualTo("Congratulations, you won!"),
                () -> assertThat(finalResult.responseDto().getHash()).isEqualTo(ticketHash));

    }
}



