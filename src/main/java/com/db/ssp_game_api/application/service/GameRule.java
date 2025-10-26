package com.db.ssp_game_api.application.service;

import com.db.ssp_game_api.domain.model.GameMove;
import com.db.ssp_game_api.domain.model.GameResult;

public interface GameRule {
    GameResult determineResult(GameMove playerMove, GameMove computerMove);
}
