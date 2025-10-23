package com.db.ssp_game_api.domain.model;

import lombok.Getter;

@Getter
public enum GameMove {
    STONE,
    PAPER,
    SCISSORS;

    public boolean beats(GameMove otherMove) {
        return (this == STONE && otherMove == SCISSORS) ||
                (this == PAPER && otherMove == STONE) ||
                (this == SCISSORS && otherMove == PAPER);
    }
}
