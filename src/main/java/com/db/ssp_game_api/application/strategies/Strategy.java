package com.db.ssp_game_api.application.strategies;

import com.db.ssp_game_api.domain.model.GameMove;

public interface Strategy {
    GameMove getMove();
    StrategyName getName();
}
