package com.db.ssp_game_api.domain.strategy;


import com.db.ssp_game_api.domain.model.GameMove;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RandomStrategyTest {

    @Mock
    private Random random;

    @InjectMocks
    private RandomStrategy randomStrategy;


    @Test
    void get_name_should_return_random() {
        assertEquals(StrategyName.RANDOM, randomStrategy.getName());
    }

    @Test
    void get_move_should_return_expected_move_when_random_returns_specific_index() {
        when(random.nextInt(anyInt())).thenReturn(1);

        GameMove move = randomStrategy.getMove();

        assertEquals(GameMove.PAPER, move);
        verify(random).nextInt(GameMove.values().length);
    }
}
