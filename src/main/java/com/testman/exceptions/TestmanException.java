package com.testman.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: kunitin
 * Created: 30/03/22
 * Info: Exception thrown with minimum details
 **/

@Data
@NoArgsConstructor
public class TestmanException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String message;
    private TestmanErrorCodes testmanErrorCodes;
    private Exception exception;

    public TestmanException(String msg, TestmanErrorCodes codes) {
        super(msg);
        message = msg;
        testmanErrorCodes = codes;
    }

    //To call this when exception trace is to be exposed in error response body. Not recommended
    public TestmanException(String msg, TestmanErrorCodes codes, Exception ex) {
        super(msg);
        message = msg;
        testmanErrorCodes = codes;
        exception = ex;
    }

}
