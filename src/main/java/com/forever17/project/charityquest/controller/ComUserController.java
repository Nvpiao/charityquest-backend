package com.forever17.project.charityquest.controller;

import com.forever17.project.charityquest.enums.UserType;
import com.forever17.project.charityquest.exceptions.SystemInternalException;
import com.forever17.project.charityquest.pojos.entity.ReturnStatus;
import com.forever17.project.charityquest.services.CommonUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

/**
 * Controller of common method for both public and charity users.
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 21 Feb 2020
 * @since 1.0
 */
@Slf4j
@CrossOrigin
@RestController
@Api(tags = "ComUser")
@RequestMapping(path = "/comUser", consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ComUserController {

    private final ReturnStatus internalErrorStatus;

    private final CommonUserService commonUserService;

    @Autowired
    public ComUserController(ReturnStatus internalErrorStatus, CommonUserService commonUserService) {
        this.internalErrorStatus = internalErrorStatus;
        this.commonUserService = commonUserService;
    }


    @ResponseBody
    @ApiOperation(value = "Send email to reset password.",
            consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "email", value = "email address",
            dataTypeClass = String.class, paramType = "query", required = true)
    @GetMapping(path = "/sendResetPassword")
    public ReturnStatus sendResetPassword(@PathParam("email") String email) {
        try {
            return commonUserService.sendResetPassword(email);
        } catch (SystemInternalException e) {
            return internalErrorStatus;
        }
    }

    @ResponseBody
    @ApiOperation(value = "reset password of user.",
            consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "email", value = "email address",
                    dataTypeClass = String.class, paramType = "query", required = true),
            @ApiImplicitParam(name = "password", value = "password of user",
                    dataTypeClass = String.class, paramType = "query", required = true),
            @ApiImplicitParam(name = "code", value = "code for reset password",
                    dataTypeClass = String.class, paramType = "query", required = true)
    })
    @PostMapping(path = "/resetPassword")
    public ReturnStatus resetPassword(@RequestParam("email") String email,
                                      @RequestParam("password") String password,
                                      @RequestParam("code") String code,
                                      @RequestParam("type") int type) {
        UserType userType = UserType.getUserType(type);
        try {
            return commonUserService.resetPassword(email, password, code, userType);
        } catch (SystemInternalException e) {
            return internalErrorStatus;
        }
    }
}
