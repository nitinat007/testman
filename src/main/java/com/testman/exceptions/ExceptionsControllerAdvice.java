package com.testman.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.testman.exceptions.ErrorModels.ErrorModel;
import static com.testman.exceptions.ErrorModels.MinimalErrorModel;

/**
 * Author: kunitin
 * Created: 30/03/22
 * Info: Contains all mapping of exception to Response body
 **/


@ControllerAdvice
public class ExceptionsControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({TestmanException.class})
    public ResponseEntity<?> customException(TestmanException ex) {
        HttpStatus status = getHttpStatusFromCustomErrorMapping(ex.getTestmanErrorCodes());
        return error(status, ex);
    }

    //This way we can override a default behaviour
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorModel error = new ErrorModel("Request Argument not valid. Check request body. ", TestmanErrorCodes.TESTMAN_CLIENT_ERROR, ex.getFieldError().getDefaultMessage());
        logger.error(error);
        return new ResponseEntity<>(error, status);
    }


    private ResponseEntity<?> error(HttpStatus status, TestmanException e) {
        logger.error(" Exception: {}", e);
        boolean sendMinimalErrorModel = e.getException() == null;
        if (sendMinimalErrorModel) {
            MinimalErrorModel d = new MinimalErrorModel(e.getMessage(), e.getTestmanErrorCodes());
            return ResponseEntity.status(status).body(d);
        } else {
            ErrorModel d = new ErrorModel(e.getMessage(), e.getTestmanErrorCodes(), e.getLocalizedMessage()); //check args here
            return ResponseEntity.status(status).body(d);
        }
    }

    private HttpStatus getHttpStatusFromCustomErrorMapping(TestmanErrorCodes testmanErrorCode) {

        switch (testmanErrorCode) {
            case TEST_NOT_FOUND:
            case PROD_NOT_FOUND:
                return HttpStatus.NOT_FOUND;
            case TESTMAN_CLIENT_ERROR:
                return HttpStatus.BAD_REQUEST;
            case TESTMAN_INTERNAL_ERROR:
                return HttpStatus.INTERNAL_SERVER_ERROR;
            default:
                return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
