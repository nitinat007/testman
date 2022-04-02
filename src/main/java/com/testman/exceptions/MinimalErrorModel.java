package com.testman.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: kunitin
 * Created: 02/04/22
 * Info: A model to send minimal error message
 **/


@NoArgsConstructor
@AllArgsConstructor
@Data
public class MinimalErrorModel {
    private String errorMessage;
    private TestmanErrorCodes errorCode;
}
