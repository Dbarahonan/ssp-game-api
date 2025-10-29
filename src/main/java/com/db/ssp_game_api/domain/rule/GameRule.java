package com.db.ssp_game_api.domain.rule;

import com.db.ssp_game_api.domain.model.GameMove;
import com.db.ssp_game_api.domain.model.GameResult;

public interface GameRule {
    GameResult determineResult(GameMove playerMove, GameMove computerMove);
}
