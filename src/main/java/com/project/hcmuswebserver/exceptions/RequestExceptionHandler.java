package com.project.hcmuswebserver.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;


@ControllerAdvice
public class RequestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String error = getFirstValidationMessage(ex);
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, error);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    public String getFirstValidationMessage(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        ErrorResponse errorResponse = transform(ex);
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorResponse.getStatusCode()));
    }

    private ErrorResponse transform(Exception ex) {
        HttpStatusCode status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = "An internal server error occurred";

        if (ex instanceof ResponseStatusException) {
            System.out.println("Response status exception" + ex);
            ResponseStatusException responseStatusException = (ResponseStatusException) ex;
            status = responseStatusException.getStatusCode();
            message = responseStatusException.getReason();
        } else if (ex.getMessage() != null) {
            message = ex.getMessage();
        }

        return new ErrorResponse(status, message);
    }
}
