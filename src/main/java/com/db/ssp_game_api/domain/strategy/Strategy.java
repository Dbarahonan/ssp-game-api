package com.db.ssp_game_api.domain.strategy;

import com.db.ssp_game_api.domain.model.GameMove;

public interface Strategy {
    GameMove getMove();
    StrategyName getName();
}
