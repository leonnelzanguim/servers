package com.probeaufgabe.servers.utils.Exceptions;

public class GenericException extends AbstractRuntimeException {
    public GenericException(Throwable cause, String description){
        super(cause,ExceptionFault.GENERIC_ERROR,description);
    }
}
