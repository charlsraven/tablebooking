package com.voronina.api.exceptions.services;

public class StatusServiceException extends ServiceException {

    public StatusServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public StatusServiceException(String message) {
        super(message);
    }

    public StatusServiceException(Throwable cause) {
        super(cause);
    }
}
