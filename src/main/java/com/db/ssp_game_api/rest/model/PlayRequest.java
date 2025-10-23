package com.db.ssp_game_api.rest.model;

import com.db.ssp_game_api.domain.model.GameLevel;
import com.db.ssp_game_api.domain.model.GameMove;
import lombok.Getter;

@Getter
public class PlayRequest {
    GameMove playerMove;
    GameLevel level;
}
