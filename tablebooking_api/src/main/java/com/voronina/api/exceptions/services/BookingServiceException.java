package com.voronina.api.exceptions.services;

public class BookingServiceException extends ServiceException {

    public BookingServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookingServiceException(String message) {
        super(message);
    }

    public BookingServiceException(Throwable cause) {
        super(cause);
    }
}