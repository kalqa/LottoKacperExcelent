package pl.lotto.feature;


import com.fasterxml.jackson.databind.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.web.servlet.*;
import pl.*;
import pl.lotto.numberreceiver.dto.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = LottoApplication.class)
@AutoConfigureMockMvc
public class LottoUserWinnerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void happy_path_when_user_played_and_after_few_days_want_to_know_if_won() throws Exception {
        //step 1: user send POST to /inputNumbers with numbers (1,2,3,4,5,6)
        //Given
        //When
        ResultActions perform = mockMvc.perform(post("/inputNumbers"));

        //Then
        MvcResult mvcResult = perform.andExpect(status().isOk()).andReturn();

        String json = mvcResult.getResponse().getContentAsString();
        NumberReceiverResponseDto result = objectMapper.readValue(json, NumberReceiverResponseDto.class);

        assertThat(result.message()).isEqualTo("success");
        assertThat(result.ticketDto().getDrawDate()).isEqualTo("success");


    }
}
