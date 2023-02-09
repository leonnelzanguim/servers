package com.probeaufgabe.servers.utils.Exceptions;

public class Invalid_URI_Exception extends AbstractRuntimeException{
    public Invalid_URI_Exception(Throwable cause){
        super(cause, ExceptionFault.INVALID_URI_EXCEPTION);
    }
    public Invalid_URI_Exception(Throwable cause, String description){
        super(cause, ExceptionFault.INVALID_URI_EXCEPTION, description);
    }

}
