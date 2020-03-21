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
    public static final String RETURN_USER_DOES_NOT_EXIST_ERROR = "User Does not exist!";
    public static final String RETURN_PASSWORD_DUPLICATED_ERROR = "The new password cannot be duplicated with the original password!";
    public static final String RETURN_CHANGE_PASSWORD_SUCCESS = "Password reset complete!";
    public static final String RETURN_SEND_RESET_EMAIL_SUCCESS = "Reset mail sent successfully.";
    public static final String RETURN_RESET_PASSWORD_CODE_EXPIRE_OR_USED = "Code has expired or is used!";
    public static final String RETURN_RESET_PASSWORD_CODE_CAN_USE = "Code can be used.";
    public static final String RETURN_RESET_PASSWORD_SUCCESS = "Password reset successful.";
    public static final String RETURN_USER_INFO_GET_SUCCESS = "User information obtained successfully.";
    public static final String RETURN_USER_INFO_UPDATE_SUCCESS = "User information updated successfully.";
    public static final String RETURN_GET_DRAFT_MESSAGE_SUCCESS = "Got draft list successfully.";
    public static final String RETURN_CHARITY_USER_ADDED = "Charity User Registered Successfully!";
    public static final String RETURN_CHARITY_USER_LOGIN_SUCCESS = "Charity User login successfully";
    public static final String RETURN_MESSAGE_DOES_NOT_SAVED = "Message not saved!";
    public static final String RETURN_MESSAGE_SEND_SUCCESS = "Message sent successfully.";
    public static final String RETURN_CREATE_MESSAGE_SUCCESS = "Message created successfully.";
    public static final String RETURN_UPDATE_MESSAGE_SUCCESS = "Message updated successfully.";
    public static final String RETURN_MESSAGE_NOTHING_SEND = "No message will be sent.";
    public static final String RETURN_HOME_DETAIL_GET_SUCCESS = "Homepage information obtained successfully.";
    public static final String RETURN_DASHBOARD_DONATION_AMOUNT_GET_SUCCESS = "Dashboard: donation amount obtained successfully.";
    public static final String RETURN_DASHBOARD_DONATION_HISTORY_GET_SUCCESS = "Dashboard: donation history obtained successfully.";
    public static final String RETURN_DASHBOARD_DONATION_LOCATION_GET_SUCCESS = "Dashboard: donation location obtained successfully.";
    public static final String RETURN_CHARITY_LIST_GET_SUCCESS = "List of Charities get successfully.";
    public static final String RETURN_URL_CAN_NOT_BLANK = "URL can not be blank!";
    public static final String RETURN_URL_CAN_NOT_BE_USED = "URL can not be used!";
    public static final String RETURN_URL_CAN_BE_USED = "URL can be used.";

    // md5 algorithm string
    public static final String MD5 = "MD5";
    public static final String MD5_PASSWORD = "md5Password";
    public static final String NO_SUCH_ALGORITHM_ERROR = "No such algorithm error.";
    public static final String UNSUPPORTED_ENCODING_ERROR = "Unsupported encoding error!";

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
    public static final String VALID_MESSAGE_CONTENT_CANNOT_BLANK = "Content can not be blank!";
    public static final String VALID_CHARITY_ID_BLANK_WARN = "Charity ID can not be blank!";
    public static final String VALID_MESSAGE_SUBJECT_CANNOT_BLANK = "Subject can not be blank!";

    // log
    public static final String LOG_INCORRECT_PASSWORD = "UserEmail: {%s}, Error: Incorrect user password!";
    public static final String LOG_USER_DOES_NOT_EXIST_EMAIL = "UserEmail: {%s}, Error: User does not exist!";
    public static final String LOG_USER_LOGIN_SUCCESS = "UserEmail: {%s}, Info: User login successfully.";
    public static final String LOG_USER_ALREADY_LOGIN = "UserEmail: {%s}, Info: User has already logged in.";
    public static final String LOG_USER_DOES_NOT_EXIST_ID = "UserID: {%s}, Error: User does not exist!";
    public static final String LOG_CHANGE_PASSWORD_DUPLICATE = "UserID: {%s}, Error: Duplicate Password!";
    public static final String LOG_MESSAGE_DOES_NOT_EXIST = "MessageID: {%s}, Error: Message Does not exist!";
    public static final String LOG_MESSAGE_SEND_FAILED = "MessageID: {%s}, SendTo: {%s}, Error: Message sending failed!";
    public static final String LOG_FUNDRAISING_URL_CAN_NOT_BE_USED = "URL: {%s}, ERROR: Url already exists!";

    // mail
    public static final String MAIL_SUBJECT_RESET_PASSWORD = "Password Reset";
    public static final String MAIL_TEXT_RESET_PASSWORD = "Use the following link to reset your password";
    public static final String MAIL_TEXT_RESET_PASSWORD_TYPE = "&type=";
    public static final String MAIL_TEXT_RESET_PASSWORD_EMAIL = "&email=";
    public static final String MAIL_TEXT_RESET_PASSWORD_CODE = "?code=";

    // return data
    public static final String DATA_PAGE_HELPER_NOW_PAGE = "nowPage";
    public static final String DATA_PAGE_HELPER_TOTAL_NUMS = "totalNums";
    public static final String DATA_PAGE_HELPER_TOTAL_PAGES = "totalPages";
    public static final String DATA_PAGE_HELPER_DATA = "data";
    public static final String DATA_MESSAGE_ID = "messageId";
    public static final String DATA_TOTAL_DONATION = "totalDonation";
    public static final String DATA_TOTAL_DONATOR = "totalDonators";
    public static final String DATA_MOM_RATIO = "momRatio";
    public static final String DATA_YOY_RATIO = "yoyRatio";
    public static final String DATA_DATE = "date";
    public static final String DATA_DONATION = "donation";

    // header
    public static final String HEADER_REQUEST_TOKEN = "token";
    public static final String HEADER_SAME_SITE_COOKIE = "Set-Cookie";
    public static final String HEADER_VALUE_SAME_SITE_NONE = "HttpOnly; SameSite=None";

    // prefix
    public static final String CODE_PREFIX = "code";

    // sql
    public static final String SQL_ORDER_MODIFY_TIME_DESC = "modify_time desc";

    // thread pool
    public static final String THREAD_POOL_NAME_PREFIX = "ThreadPoolTaskExecutor-";
    public static final String THREAD_POOL_PREFIX = "thread";

    // email template
    public static final String MESSAGE_TEMPLATE_FIRST_NAME = "$FirstName";
    public static final String MESSAGE_TEMPLATE_LAST_NAME = "$LastName";

    // mysql
    public static final String SEARCH_WILD_CARD = "%";
    public static final String ESCAPE_REPLACE = "\\";
    public static final String[] ESCAPE_STRINGS = {"\\", "$", "|", "%", "_", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}"};
}
