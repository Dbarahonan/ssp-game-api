package com.db.ssp_game_api.domain.rule;

import com.db.ssp_game_api.domain.model.GameMove;
import com.db.ssp_game_api.domain.model.GameResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SSPGameRuleTest {

    private SSPGameRule gameRule;

    @BeforeEach
    void setUp() {
        gameRule = new SSPGameRule();
    }

    @Test
    void determine_result_should_return_draw_when_moves_are_equal() {
        for (GameMove move : GameMove.values()) {
            assertEquals(GameResult.DRAW, gameRule.determineResult(move, move),
                    "Expected DRAW when both moves are " + move);
        }
    }

    @Test
    void determine_result_should_return_win_or_lose_correctly() {
        // STONE
        assertEquals(GameResult.WIN, gameRule.determineResult(GameMove.STONE, GameMove.SCISSORS));
        assertEquals(GameResult.LOSE, gameRule.determineResult(GameMove.STONE, GameMove.PAPER));

        // PAPER
        assertEquals(GameResult.WIN, gameRule.determineResult(GameMove.PAPER, GameMove.STONE));
        assertEquals(GameResult.LOSE, gameRule.determineResult(GameMove.PAPER, GameMove.SCISSORS));

        // SCISSORS
        assertEquals(GameResult.WIN, gameRule.determineResult(GameMove.SCISSORS, GameMove.PAPER));
        assertEquals(GameResult.LOSE, gameRule.determineResult(GameMove.SCISSORS, GameMove.STONE));
    }
}
