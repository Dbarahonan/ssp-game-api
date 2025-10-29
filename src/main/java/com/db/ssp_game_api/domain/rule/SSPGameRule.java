package com.db.ssp_game_api.domain.rule;

import com.db.ssp_game_api.domain.model.GameMove;
import com.db.ssp_game_api.domain.model.GameResult;
import org.springframework.stereotype.Service;

@Service
public class SSPGameRule implements GameRule {
    @Override
    public GameResult determineResult(GameMove playerMove, GameMove computerMove) {
        if (playerMove.equals(computerMove)) {
            return GameResult.DRAW;
        }
        return playerMove.beats(computerMove) ? GameResult.WIN : GameResult.LOSE;
    }
}
