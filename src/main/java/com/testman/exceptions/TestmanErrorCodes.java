package com.testman.exceptions;

/**
 * Author: kunitin
 * Created: 30/03/22
 * Info: Contains all error codes for the product. On adding any code here, add this in the switch statement of ExceptionsControllerAdvice.getHttpStatusFromCustomErrorMapping(..) method
 **/

public enum TestmanErrorCodes {

    //generic exception. Prefer not to use this. Let this be on the top
    TESTMAN_ERROR,

    //product related codes
    PROD_NOT_FOUND,
    FAILED_TO_DELETE_PRODUCT,

    // test category
    TEST_CATEGORY_NOT_FOUND,
    FAILED_TO_DELETE_TESTCATEGORY,

    //test related coded
    TEST_NOT_FOUND,

    //Internal error
    TESTMAN_INTERNAL_ERROR,

    //Client error
    TESTMAN_CLIENT_ERROR,

}
