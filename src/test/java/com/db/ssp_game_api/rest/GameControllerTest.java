package com.db.ssp_game_api.rest;

import com.db.ssp_game_api.application.service.SSPGameService;
import com.db.ssp_game_api.domain.model.GameMove;
import com.db.ssp_game_api.domain.model.GameResult;
import com.db.ssp_game_api.domain.strategy.StrategyName;
import com.db.ssp_game_api.rest.model.PlayRequest;
import com.db.ssp_game_api.rest.model.PlayResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class GameControllerTest {
    private MockMvc mockMvc;

    @Mock
    private SSPGameService gameService;

    @InjectMocks
    private GameController gameController;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(gameController).build();
    }

    @Test
    void play_should_return_game_result() throws Exception {
        PlayRequest request = PlayRequest.builder()
                .playerMove(GameMove.STONE)
                .strategyName(StrategyName.RANDOM)
                .build();

        PlayResponse response = PlayResponse.builder()
                .computerMove(GameMove.PAPER)
                .result(GameResult.LOSE)
                .build();

        when(gameService.play(request.getPlayerMove(), request.getStrategyName()))
                .thenReturn(response);

        mockMvc.perform(post("/api/v1/ssp/play")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.computerMove").value("PAPER"))
                .andExpect(jsonPath("$.result").value("LOSE"));
    }
}
