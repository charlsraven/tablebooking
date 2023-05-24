package com.voronina.api.exceptions.services;

public class PlaceServiceException extends ServiceException {

    public PlaceServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlaceServiceException(String message) {
        super(message);
    }

    public PlaceServiceException(Throwable cause) {
        super(cause);
    }
}
