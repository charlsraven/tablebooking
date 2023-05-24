package com.voronina.api.exceptions.services;

public class GuestServiceException extends ServiceException {

    public GuestServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public GuestServiceException(String message) {
        super(message);
    }

    public GuestServiceException(Throwable cause) {
        super(cause);
    }
}