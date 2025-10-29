package com.db.ssp_game_api.rest;


import com.db.ssp_game_api.application.service.SSPGameService;
import com.db.ssp_game_api.rest.model.PlayRequest;
import com.db.ssp_game_api.rest.model.PlayResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/ssp")
@RequiredArgsConstructor
public class GameController {
    private final SSPGameService gameService;

    @Operation(summary = "Play stone, paper, scissors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Game result"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @PostMapping("/play")
    public ResponseEntity<PlayResponse> play(@RequestBody @Valid PlayRequest playRequest){
        PlayResponse response = gameService.play(playRequest.getPlayerMove(), playRequest.getStrategyName());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
