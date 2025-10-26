package com.db.ssp_game_api.application.service.exceptions;

import org.springframework.http.HttpStatus;

public class GameException extends RuntimeException {
    public GameException(String message, Throwable cause) {
        super(message, cause);
    }
}