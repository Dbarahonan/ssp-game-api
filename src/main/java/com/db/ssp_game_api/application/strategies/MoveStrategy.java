package com.db.ssp_game_api.application.strategies;

import com.db.ssp_game_api.domain.model.GameLevel;
import com.db.ssp_game_api.domain.model.GameMove;

public interface MoveStrategy {
    GameMove getMove();
    GameLevel getLevel();
}
