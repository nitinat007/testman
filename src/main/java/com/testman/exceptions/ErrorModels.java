package com.testman.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: kunitin
 * Created: 30/03/22
 * Info: Contains the error response format
 **/


public class ErrorModels {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ErrorModel {
        private String errorMessage;
        private TestmanErrorCodes errorCode;
        private String errorDetails;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MinimalErrorModel {
        private String errorMessage;
        private TestmanErrorCodes errorCode;
    }
}
