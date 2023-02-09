package com.probeaufgabe.servers.utils.Exceptions;

import lombok.Getter;

@Getter
public enum ExceptionFault {

    GENERIC_ERROR("Unknown error occurred",404 ),
    INVALID_URI_EXCEPTION("Error the provided URI is invalid",404),
    TIMEOUT_PARSE_EXCEPTION("Error timeout waiting for a response from server",404);

    private final String message;
    private final int code;

    ExceptionFault(String message, int code) {

        this.message = message;
        this.code = code;
    }
}
