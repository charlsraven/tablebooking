package com.voronina.gateway.controllers.rest.advice;

import com.voronina.api.exceptions.dto.ErrorEntity;
import com.voronina.api.exceptions.services.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackages = "com.voronina.gateway.controllers.rest")
public class GeneralRestControllerAdvice {
    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorEntity handleNotFoundExceptions(ServiceException serviceException) {
        log.error("Thrown ", serviceException);
        return new ErrorEntity(HttpStatus.INTERNAL_SERVER_ERROR.toString(), serviceException.getMessage());
    }
}
