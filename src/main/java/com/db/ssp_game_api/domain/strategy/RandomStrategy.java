package com.db.ssp_game_api.domain.strategy;

import com.db.ssp_game_api.domain.model.GameMove;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
public class RandomStrategy implements Strategy {

    private final String LOG_HEADER = "[API][RandomMoveStrategy]";
    private final Random random = new Random();

    @Override
    public GameMove getMove() {
        log.info("{} getting random computer move",LOG_HEADER);
        GameMove[] moves = GameMove.values();
        return moves[random.nextInt(moves.length)];
    }

    @Override
    public StrategyName getName() {
        return StrategyName.RANDOM;
    }
}
