package com.db.ssp_game_api.rest;


import com.db.ssp_game_api.application.service.GameService;
import com.db.ssp_game_api.domain.model.GameResult;
import com.db.ssp_game_api.rest.model.PlayRequest;
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
    public ResponseEntity<GameResult> play(@RequestBody PlayRequest playRequest){
        return new ResponseEntity<>(gameService.play(playRequest.getPlayerMove(),playRequest.getLevel()), HttpStatus.OK);
    }
}
