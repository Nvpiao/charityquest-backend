package com.forever17.project.charityquest.constants;

/**
 * Constants of Project
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 15 Feb 2020
 * @since 1.0
 */
public class CharityConstants {
    // swagger2
    public static final String SWAGGER2_BASE_PACKAGE = "com.forever17.project.charityquest.controller";
    public static final String SWAGGER2_TITLE = "CharityQuest-swagger2";
    public static final String SWAGGER2_DESC = "CharityQuest-Restful-API-Doc";
    public static final String SWAGGER2_VERSION = "1.0";
    public static final String SWAGGER2_TERMS_OF_SERVICE = "http://teamwork:4999/web/#/p/c0db6182fac4d3b1066e4977428a2a8c";
    public static final String SWAGGER2_CONTACT_NAME = "Team17";
    public static final String SWAGGER2_CONTACT_URL = SWAGGER2_TERMS_OF_SERVICE;
    public static final String SWAGGER2_CONTACT_EMAIL = "MLiu54@sheffield.ac.uk";

    // return messages
    public static final String RETURN_SYSTEM_INTERNAL_ERROR = "Internal System Error!";
    public static final String RETURN_VALID_ERROR = "Parameter Validation Failed.";
    public static final String RETURN_PUBLIC_USER_ADDED = "Public User Registered Successfully!";
    public static final String RETURN_EMAIL_CAN_BE_USED = "Email Address can be used.";
    public static final String RETURN_EMAIL_CAN_NOT_BE_USED = "Email Address can Not be used!";
    public static final String RETURN_USER_NOT_LOGIN = "User is not logged in!";
    public static final String RETURN_USER_LOGIN_SUCCESS = "Public User login successfully";
    public static final String RETURN_EMAIL_OR_PASSWORD_ERROR = "Email or Password is wrong!";
    public static final String RETURN_USER_HAS_NO_PERMISSION = "User has no permission to access";
    public static final String RETURN_USER_DOES_NOT_EXIST_ERROR = "User Does not exist.";
    public static final String RETURN_PASSWORD_DUPLICATED_ERROR = "The new password cannot be duplicated with the original password!";
    public static final String RETURN_CHANGE_PASSWORD_SUCCESS = "Password reset complete!";

    // md5 algorithm string
    public static final String MD5 = "MD5";
    public static final String NO_SUCH_ALGORITHM_ERROR = "No such algorithm error.";

    // validation information
    public static final String VALID_EMAIL_NOT_VALID_WARN = "Email not valid!";
    public static final String VALID_PASSWORD_BLANK_WARN = "Password can not be blank!";
    public static final String VALID_TEL_BLANK_WARN = "Telephone number can not be blank!";
    public static final String VALID_URL_BLANK_WARN = "URL can not be blank!";
    public static final String VALID_START_TIME_BLANK_WARN = "Start time can not be blank!";
    public static final String VALID_END_TIME_BLANK_WARN = "End time can not be blank!";
    public static final String VALID_UNDER_MIN_MONEY_WARN = "Target Money can not be lower than 1 pound!";
    public static final String VALID_UPPER_MAX_MONEY_WARN = "Target Money can not be Higher than 500 pounds!";
    public static final String VALID_CHARITY_NAME_BLANK_WARN = "Charity name can not be blank!";
    public static final String VALID_CHARITY_NO_BLANK_WARN = "Charity No. can not be blank!";
    public static final String VALID_TITLE_BLANK_WARN = "Title can not be blank!";

    // header
    public static final String HEADER_REQUEST_TOKEN = "token";

    // log
    public static final String LOG_INCORRECT_PASSWORD = "UserEmail: {%s}, Error: Incorrect user password!";
    public static final String LOG_USER_DOES_NOT_EXIST_EMAIL = "UserEmail: {%s}, Error: User does not exist!";
    public static final String LOG_USER_LOGIN_SUCCESS = "UserEmail: {%s}, Info: User login successfully.";
    public static final String LOG_USER_ALREADY_LOGIN = "UserEmail: {%s}, Info: User has already logged in.";
    public static final String LOG_USER_DOES_NOT_EXIST_ID = "UserID: {%s}, Error: User does not exist!";
    public static final String LOG_CHANGE_PASSWORD_DUPLICATE = "UserID: {%s}, Error: Duplicate Password!";

    // prefix
    public static final String CODE_PREFIX = "code";
}
