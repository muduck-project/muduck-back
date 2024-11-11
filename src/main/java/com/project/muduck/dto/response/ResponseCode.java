package com.project.muduck.dto.response;

public interface ResponseCode {
    
    String SUCCESS = "SU";
    
    String VALIDATION_FAILED = "VF";
    String DUPLICATED_USER_ID = "DI";
    String DUPLICATED_TEL_NUMBER = "DT";

    String NO_EXIST_USER_ID = "NI";
    String NO_EXIST_TEL_NUMBER = "NT";
    String NO_EXIST_USER_ID_AND_TEL_NUMBER = "NIT";
    String PASSWORD_MISMATCH = "PM";

    String SIGN_IN_FAIL = "SF";
    String AUTHENTICATION_FAIL = "AF";

    String TEL_AUTH_FAIL = "TAF";
    String NO_PERMISSION = "NP";

    String MESSAGE_SEND_FAIL = "TF";
    String TOKEN_CREATE_FAIL = "TCF";
    String DATABASE_ERROR = "DBE";

}
