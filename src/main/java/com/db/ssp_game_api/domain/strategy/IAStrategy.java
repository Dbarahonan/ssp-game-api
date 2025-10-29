package com.db.ssp_game_api.domain.strategy;

import com.db.ssp_game_api.domain.model.GameMove;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class IAStrategy implements Strategy {
    private static final String LOG_HEADER = "[API][IAMoveStrategy]";
    private final ChatClient chatClient;

    public IAStrategy(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @Override
    public GameMove getMove() {
        log.info("{} getting IA computer move", LOG_HEADER);
        String prompt = """
            You are an AI playing a game of Stone, Paper, Scissors at HARD difficulty. 
            Your goal is to choose a move that is unpredictable and strategic. 
            The possible moves are: STONE, PAPER, SCISSORS.
            Respond with exactly one word in english and in uppercase, representing your chosen move (e.g., STONE, PAPER, or SCISSORS).
            """;

        String response = Objects.requireNonNull(chatClient.prompt(prompt).call().content()).trim().toUpperCase();
        log.info("{} IA response: {}", LOG_HEADER, response);
        return GameMove.valueOf(response);
    }

    @Override
    public StrategyName getName() {
        return StrategyName.IA;
    }
}
