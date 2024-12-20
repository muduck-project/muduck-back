package com.project.muduck.dto.response;

public interface ResponseMessage {
    String SUCCESS = "Success.";

    String VALIDATION_FAILED = "Validation failed.";
    String DUPLICATED_USER_ID = "Duplicated user id.";
    String DUPLICATED_TEL_NUMBER = "Duplicated user tel number.";


    String NO_EXIST_USER_ID = "No exist user id.";
    String NO_EXIST_TEL_NUMBER = "No exist tel number";
    String NO_EXIST_USER_ID_AND_TEL_NUMBER = "No exist user id and tel number";
    String PASSWORD_MISMATCH = "Password mismatch.";

    String SIGN_IN_FAIL = "Sign in failed.";
    String AUTHENTICATION_FAIL = "Authentication failed.";

    String TEL_AUTH_FAIL = "Tel number authentication failed.";
    String NO_PERMISSION = "No permission.";

    String MESSAGE_SEND_FAIL = "Auth number send failed.";
    String TOKEN_CREATE_FAIL = "Token creation failed.";
    String DATABASE_ERROR = "Database error.";
}
