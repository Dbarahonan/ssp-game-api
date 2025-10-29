package com.db.ssp_game_api.integration;

import com.db.ssp_game_api.domain.model.GameMove;
import com.db.ssp_game_api.domain.strategy.StrategyName;
import com.db.ssp_game_api.rest.model.PlayRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GameIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void play_should_return_real_game_result() throws Exception {
        PlayRequest request = PlayRequest.builder()
                .playerMove(GameMove.STONE)
                .strategyName(StrategyName.RANDOM)
                .build();

        mockMvc.perform(post("/api/v1/ssp/play")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.computerMove").exists())
                .andExpect(jsonPath("$.result").exists());
    }
}
