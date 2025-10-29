package com.db.ssp_game_api.domain.strategy;

import com.db.ssp_game_api.domain.model.GameMove;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IAStrategyTest {
    @Mock
    private ChatClient.Builder chatBuilder;

    @Mock
    private ChatClient chatClient;

    @Mock
    private ChatClient.ChatClientRequestSpec requestSpec;

    @Mock
    private ChatClient.CallResponseSpec chatResponse;

    @InjectMocks
    private IAStrategy iaStrategy;

    @BeforeEach
    void setUp() {
        when(chatBuilder.build()).thenReturn(chatClient);
        iaStrategy = new IAStrategy(chatBuilder);
    }

    @Test
    void get_name_should_return_IA() {
        assertEquals(StrategyName.IA, iaStrategy.getName());
    }

    @Test
    void get_move_should_return_expected_game_move() {
        when(chatClient.prompt(anyString())).thenReturn(requestSpec);
        when(requestSpec.call()).thenReturn(chatResponse);
        when(chatResponse.content()).thenReturn("PAPER");

        GameMove move = iaStrategy.getMove();

        assertEquals(GameMove.PAPER, move);

        verify(chatClient).prompt(anyString());
    }
}
