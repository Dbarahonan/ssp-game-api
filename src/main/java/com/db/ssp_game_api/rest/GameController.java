package com.db.ssp_game_api.rest;


import com.db.ssp_game_api.application.service.GameService;
import com.db.ssp_game_api.domain.model.GameResult;
import com.db.ssp_game_api.rest.model.PlayRequest;
import com.db.ssp_game_api.rest.model.PlayResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

    @Operation(summary = "Play stone, paper, scissors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Game result"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PostMapping("/play")
    public ResponseEntity<PlayResponse> play(@RequestBody PlayRequest playRequest){
        GameResult result = gameService.play(playRequest.getPlayerMove(), playRequest.getLevel());
        PlayResponse response = PlayResponse.builder()
                .result(result)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
