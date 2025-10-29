package com.db.ssp_game_api.application.exceptions;

public class GameException extends RuntimeException {
    public GameException(String message, Throwable cause) {
        super(message, cause);
    }
}