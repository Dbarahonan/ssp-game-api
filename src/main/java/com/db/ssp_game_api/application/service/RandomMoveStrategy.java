package com.db.ssp_game_api.application.service;

import com.db.ssp_game_api.domain.model.GameLevel;
import com.db.ssp_game_api.domain.model.GameMove;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
public class RandomMoveStrategy implements MoveStrategy {

    private final String LOG_HEADER = "[API][RandomMoveStrategy]";
    private final Random random = new Random();

    @Override
    public GameMove getMove() {
        log.info("{} getting random computer move",LOG_HEADER);
        GameMove[] moves = GameMove.values();
        return moves[random.nextInt(moves.length)];
    }

    @Override
    public GameLevel getLevel() {
        return GameLevel.EASY;
    }
}
