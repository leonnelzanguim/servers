package com.probeaufgabe.servers.utils.Exceptions;

import lombok.ToString;


public abstract class AbstractRuntimeException extends RuntimeException {
    private final String errorMessage;

    private final String errorDescription;
    private final String errorDetailMessage;

    protected AbstractRuntimeException( Throwable cause, ExceptionFault exceptionFault) {
        super(exceptionFault.getMessage());
        this.errorMessage = exceptionFault.getMessage();
        this.errorDescription = "";
        this.errorDetailMessage = cause.getClass().getSimpleName() + " " + cause;
    }

    protected AbstractRuntimeException( Throwable cause, ExceptionFault exceptionFault,String description) {
        super(description+" / "+exceptionFault.getMessage());
        this.errorMessage = exceptionFault.getMessage();
        this.errorDescription = description;
        this.errorDetailMessage = cause.getClass().getSimpleName() + " " + cause;
    }


}
