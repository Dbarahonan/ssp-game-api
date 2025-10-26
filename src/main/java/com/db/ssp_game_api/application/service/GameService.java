package com.db.ssp_game_api.application.service;

import com.db.ssp_game_api.application.service.exceptions.GameException;
import com.db.ssp_game_api.domain.model.GameLevel;
import com.db.ssp_game_api.domain.model.GameMove;
import com.db.ssp_game_api.domain.model.GameResult;
import com.db.ssp_game_api.rest.model.PlayResponse;
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
    private final GameRule gameRule;

    public GameService(List<MoveStrategy> moveStrategies, GameRule gameRule) {
        this.moveStrategyMap = moveStrategies.stream()
                .collect(Collectors.toMap(MoveStrategy::getLevel, s -> s));
        this.gameRule = gameRule;
    }

    public PlayResponse play(GameMove playerMove, GameLevel level) {
        log.info("{} player chooses {} and level {}", LOG_HEADER, playerMove, level);
        try {
            MoveStrategy strategy = moveStrategyMap.get(level);
            GameMove computerMove = strategy.getMove();

            log.info("{} computer chooses {}", LOG_HEADER, computerMove);

            GameResult result = gameRule.determineResult(playerMove, computerMove);

            log.info("{} game result is {}", LOG_HEADER, result);

            return PlayResponse.builder()
                    .result(result)
                    .computerMove(computerMove)
                    .build();
        } catch (Exception e) {
            log.error("{} Error during game play with playerMove={} and level={}",
                    LOG_HEADER, playerMove, level, e);
            throw new GameException(
                    String.format("Failed to play game for move '%s' at level '%s'", playerMove, level),
                    e
            );
        }
    }
}
