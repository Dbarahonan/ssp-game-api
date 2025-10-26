package com.db.ssp_game_api.application.service;

import com.db.ssp_game_api.domain.model.GameMove;
import com.db.ssp_game_api.domain.model.GameResult;
import org.springframework.stereotype.Service;

@Service
public class StonePaperScissorsRule implements GameRule {
    @Override
    public GameResult determineResult(GameMove playerMove, GameMove computerMove) {
        if (playerMove.equals(computerMove)) {
            return GameResult.DRAW;
        }
        return playerMove.beats(computerMove) ? GameResult.WIN : GameResult.LOSE;
    }
}
