package com.db.ssp_game_api.application.service;

import com.db.ssp_game_api.domain.rule.GameRule;
import com.db.ssp_game_api.application.exceptions.GameException;
import com.db.ssp_game_api.domain.strategy.Strategy;
import com.db.ssp_game_api.domain.strategy.StrategyName;
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
    private final Map<StrategyName, Strategy> strategyMap;
    private final GameRule gameRule;

    public GameService(List<Strategy> moveStrategies, GameRule gameRule) {
        this.strategyMap = moveStrategies.stream()
                .collect(Collectors.toMap(Strategy::getName, s -> s));
        this.gameRule = gameRule;
    }

    public PlayResponse play(GameMove playerMove, StrategyName strategyName) {
        log.info("{} player chooses {} and strategy {}", LOG_HEADER, playerMove, strategyName);
        try {
            Strategy strategy = strategyMap.get(strategyName);
            GameMove computerMove = strategy.getMove();

            log.info("{} computer chooses {}", LOG_HEADER, computerMove);

            GameResult result = gameRule.determineResult(playerMove, computerMove);

            log.info("{} game result is {}", LOG_HEADER, result);

            return PlayResponse.builder()
                    .result(result)
                    .computerMove(computerMove)
                    .build();
        } catch (Exception e) {
            log.error("{} Error during game play with playerMove={} and strategy={}",
                    LOG_HEADER, playerMove, strategyName, e);
            throw new GameException(
                    String.format("Failed to play game for move '%s' and strategy '%s'", playerMove, strategyName),
                    e
            );
        }
    }
}
