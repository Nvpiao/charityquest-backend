package com.forever17.project.charityquest.controller.global;

import com.forever17.project.charityquest.constants.CharityConstants;
import com.forever17.project.charityquest.constants.CharityErrorCodes;
import com.forever17.project.charityquest.enums.StatusInfo;
import com.forever17.project.charityquest.utils.ReturnStatus;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;
import java.util.List;

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
        return new ReturnStatus(errors,
                CharityErrorCodes.GLOBAL_VALID_ERROR, StatusInfo.FAIL);
    }

    /**
     * handle all exception out of control.
     *
     * @param ex exception
     * @return class of ReturnStatus
     */
    @ResponseBody
    @ExceptionHandler(value = {Exception.class})
    protected ReturnStatus handleExtraException(RuntimeException ex) {
        // log
        log.error(CharityConstants.RETURN_UNKNOWN_ERROR, ex);
        return new ReturnStatus(Collections.singletonList(ex.getMessage()),
                CharityErrorCodes.GLOBAL_UNKNOWN_ERROR, StatusInfo.FAIL);
    }
}
