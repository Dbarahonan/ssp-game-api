package com.db.ssp_game_api.rest.model;

import com.db.ssp_game_api.domain.strategy.StrategyName;
import com.db.ssp_game_api.domain.model.GameMove;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PlayRequest {
    @NotNull
    GameMove playerMove;
    @NotNull
    StrategyName strategyName;
}
