package com.db.ssp_game_api.application.service;

import com.db.ssp_game_api.domain.model.GameMove;
import com.db.ssp_game_api.domain.strategy.StrategyName;
import com.db.ssp_game_api.rest.model.PlayResponse;

public interface GameService {
    PlayResponse play(GameMove playerMove, StrategyName strategyName);
}
