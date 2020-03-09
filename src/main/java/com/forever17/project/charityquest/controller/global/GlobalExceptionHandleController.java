package com.forever17.project.charityquest.controller.global;

import com.forever17.project.charityquest.constants.CharityCodes;
import com.forever17.project.charityquest.constants.CharityConstants;
import com.forever17.project.charityquest.enums.StatusType;
import com.forever17.project.charityquest.exceptions.UserNotLogInException;
import com.forever17.project.charityquest.pojos.entity.ReturnStatus;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Objects;

/**
 * Exception Handler
 * It catches exceptions while throwing by any Classes
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 15 Feb 2020
 * @since 1.0
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandleController {

    /**
     * validation of entity
     *
     * @param ex MethodArgumentNotValidException
     * @return class of ReturnStatus
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ReturnStatus handleValidExceptions(
            MethodArgumentNotValidException ex) {
        List<String> errors = Lists.newArrayList();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String errorMessage = error.getDefaultMessage();
            errors.add(errorMessage);
        });
        // log
        log.error(CharityConstants.RETURN_VALID_ERROR, ex);
        return new ReturnStatus(errors, CharityCodes.GLOBAL_VALID_ERROR, StatusType.WARN);
    }

    /**
     * handle user login exception throw by aop
     *
     * @param ex UserNotLogInException
     * @return class of ReturnStatus
     */
    @ResponseBody
    @ExceptionHandler(value = {UserNotLogInException.class})
    public ReturnStatus handleUserNotLoginExceptions(
            UserNotLogInException ex) {
        // log
        log.error(ex.getMessage(), ex);
        return new ReturnStatus(ex.getMessage(), CharityCodes.USER_STATUS_ERROR, StatusType.FAIL);
    }

    /**
     * handle all exception out of control.
     *
     * @param ex exception
     * @return class of ReturnStatus
     */
    @ResponseBody
    @ExceptionHandler(value = {Exception.class})
    protected ReturnStatus handleExtraException(Throwable ex) {
        Throwable cause = ex.getCause();

        if (cause instanceof UserNotLogInException) {
            return handleUserNotLoginExceptions((UserNotLogInException) cause);
        }

        // log
        log.error(CharityConstants.RETURN_SYSTEM_INTERNAL_ERROR, ex);
        // directly throw
        if (Objects.isNull(cause)) {
            return new ReturnStatus(ex.getMessage(),
                    CharityCodes.GLOBAL_SYSTEM_INTERNAL_ERROR, StatusType.FAIL);
        }
        return new ReturnStatus(CharityConstants.RETURN_SYSTEM_INTERNAL_ERROR,
                CharityCodes.GLOBAL_SYSTEM_INTERNAL_ERROR, StatusType.FAIL);
    }
}
