package com.testman.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: kunitin
 * Created: 02/04/22
 * Info: Model to send detailed error response
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DetailedErrorModel extends MinimalErrorModel {
    private String errorTrace;

    public DetailedErrorModel(String errorMessage, TestmanErrorCodes testmanClientError, Exception ex) {
        this.setErrorMessage(errorMessage);
        this.setErrorTrace(ex.getMessage());
        this.setErrorCode(testmanClientError);
    }
}
