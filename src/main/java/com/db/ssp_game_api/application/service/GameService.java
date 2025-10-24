package com.db.ssp_game_api.application.service;

import com.db.ssp_game_api.domain.model.GameLevel;
import com.db.ssp_game_api.domain.model.GameMove;
import com.db.ssp_game_api.domain.model.GameResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GameService {

    private final String LOG_HEADER = "[API][GameService]";
    private final Map<GameLevel, MoveStrategy> moveStrategyMap;

    public GameService(List<MoveStrategy> moveStrategies) {
        this.moveStrategyMap = moveStrategies.stream()
                .collect(Collectors.toMap(MoveStrategy::getLevel, s -> s));
    }

    public GameResult play(GameMove playerMove, GameLevel level) {
        log.info("{} player chooses {} and level {}", LOG_HEADER, playerMove, level);
        GameMove computerMove = moveStrategyMap.get(level).getMove();
        log.info("{} computer chooses {}", LOG_HEADER, computerMove);
        if (playerMove.equals(computerMove)) {
            return GameResult.DRAW;
        }
        return playerMove.beats(computerMove) ? GameResult.WIN : GameResult.LOSE;
    }
}
