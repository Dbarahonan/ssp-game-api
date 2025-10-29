package com.db.ssp_game_api.application.service;


import com.db.ssp_game_api.application.exceptions.GameException;
import com.db.ssp_game_api.domain.model.GameMove;
import com.db.ssp_game_api.domain.model.GameResult;
import com.db.ssp_game_api.domain.rule.GameRule;
import com.db.ssp_game_api.domain.strategy.Strategy;
import com.db.ssp_game_api.domain.strategy.StrategyName;
import com.db.ssp_game_api.rest.model.PlayResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SSPGameServiceTest {
    @Mock
    private GameRule gameRule;

    @Mock
    private Strategy randomStrategy;

    @Mock
    private Strategy iaStrategy;

    private SSPGameService gameService;

    @BeforeEach
    void setUp() {
        when(randomStrategy.getName()).thenReturn(StrategyName.RANDOM);
        when(iaStrategy.getName()).thenReturn(StrategyName.IA);
        gameService = new SSPGameService(List.of(randomStrategy, iaStrategy), gameRule);
    }

    @Test
    void play_should_return_win_when_player_wins() {
        when(randomStrategy.getMove()).thenReturn(GameMove.SCISSORS);
        when(gameRule.determineResult(GameMove.STONE, GameMove.SCISSORS)).thenReturn(GameResult.WIN);

        PlayResponse response = gameService.play(GameMove.STONE, StrategyName.RANDOM);

        assertEquals(GameResult.WIN, response.getResult());
        assertEquals(GameMove.SCISSORS, response.getComputerMove());
        verify(randomStrategy, times(1)).getMove();
        verify(gameRule, times(1)).determineResult(GameMove.STONE, GameMove.SCISSORS);
    }

    @Test
    void play_should_return_draw_when_moves_are_equal() {
        when(iaStrategy.getMove()).thenReturn(GameMove.PAPER);
        when(gameRule.determineResult(GameMove.PAPER, GameMove.PAPER)).thenReturn(GameResult.DRAW);

        PlayResponse response = gameService.play(GameMove.PAPER, StrategyName.IA);

        assertEquals(GameResult.DRAW, response.getResult());
        assertEquals(GameMove.PAPER, response.getComputerMove());
        verify(iaStrategy, times(1)).getMove();
        verify(gameRule, times(1)).determineResult(GameMove.PAPER, GameMove.PAPER);
    }

    @Test
    void play_should_throw_game_exception_when_strategy_not_found() {
        Exception exception = assertThrows(GameException.class, () ->
                gameService.play(GameMove.STONE, null)
        );

        assertTrue(exception.getMessage().contains("Failed to play game"));
    }

    @Test
    void play_should_throw_game_exception_when_strategy_throws() {
        when(iaStrategy.getMove()).thenThrow(new RuntimeException("Strategy failed"));

        GameException exception = assertThrows(GameException.class, () ->
                gameService.play(GameMove.STONE, StrategyName.IA)
        );

        assertTrue(exception.getMessage().contains("Failed to play game"));
    }
}
