package com.db.ssp_game_api.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameMoveTest {
    @Test
    void beats_should_return_true_when_move_wins_against_other() {
        assertTrue(GameMove.STONE.beats(GameMove.SCISSORS));
        assertTrue(GameMove.PAPER.beats(GameMove.STONE));
        assertTrue(GameMove.SCISSORS.beats(GameMove.PAPER));
    }

    @Test
    void beats_should_return_false_when_move_loses_or_draws() {
        assertFalse(GameMove.STONE.beats(GameMove.PAPER));
        assertFalse(GameMove.PAPER.beats(GameMove.SCISSORS));
        assertFalse(GameMove.SCISSORS.beats(GameMove.STONE));

        assertFalse(GameMove.STONE.beats(GameMove.STONE));
        assertFalse(GameMove.PAPER.beats(GameMove.PAPER));
        assertFalse(GameMove.SCISSORS.beats(GameMove.SCISSORS));
    }
}
