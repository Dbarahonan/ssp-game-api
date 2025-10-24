package com.db.ssp_game_api.application.service;

import com.db.ssp_game_api.domain.model.GameLevel;
import com.db.ssp_game_api.domain.model.GameMove;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class IAMoveStrategy implements MoveStrategy {
    private final String LOG_HEADER = "[API][IAMoveStrategy]";

    @Override
    public GameMove getMove() {
        log.info("{} getting IA computer move",LOG_HEADER);
        return GameMove.PAPER;
    }

    @Override
    public GameLevel getLevel() {
        return GameLevel.HARD;
    }
}
