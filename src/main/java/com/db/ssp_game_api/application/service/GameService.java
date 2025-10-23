package com.db.ssp_game_api.application.service;

import com.db.ssp_game_api.domain.model.GameLevel;
import com.db.ssp_game_api.domain.model.GameMove;
import com.db.ssp_game_api.domain.model.GameResult;
import org.springframework.stereotype.Service;

@Service
public class GameService {
   public GameResult play(GameMove playerMove, GameLevel level) {
        GameMove computerMove = GameMove.PAPER;
        if (playerMove.equals(computerMove)) return GameResult.DRAW;
        return playerMove.beats(computerMove) ? GameResult.WIN : GameResult.LOSE;
    }
}
