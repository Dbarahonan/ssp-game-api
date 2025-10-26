package com.db.ssp_game_api.rest.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {
    private String code;
    private int status;
    private String message;
    private long timestamp;
}
