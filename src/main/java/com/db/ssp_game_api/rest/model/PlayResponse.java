package com.db.ssp_game_api.rest.model;

import com.db.ssp_game_api.domain.model.GameMove;
import com.db.ssp_game_api.domain.model.GameResult;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PlayResponse {
    GameResult result;
    GameMove computerMove;
}
