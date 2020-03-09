package com.forever17.project.charityquest.controller;

import com.forever17.project.charityquest.exceptions.SystemInternalException;
import com.forever17.project.charityquest.pojos.CharityUser;
import com.forever17.project.charityquest.pojos.entity.ReturnStatus;
import com.forever17.project.charityquest.services.CharityUserService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

@Slf4j
@CrossOrigin
@RestController
@Api(tags = "CharityUser")
@RequestMapping(path = "/charityUser", consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class CharityUserController {

    private final ReturnStatus internalErrorStatus;

    private final CharityUserService charityUserService;

    @Autowired
    public CharityUserController(ReturnStatus internalErrorStatus, CharityUserService charityUserService) {
        this.internalErrorStatus = internalErrorStatus;
        this.charityUserService = charityUserService;
    }

    @ResponseBody
    @ApiOperation("add new charity user")
    @PostMapping(path = "/register")
    public ReturnStatus register(@Valid @RequestBody CharityUser charityUser) {
        try {
            return charityUserService.addUser(charityUser);
        } catch (SystemInternalException e) {
            return internalErrorStatus;
        }
    }

    @ResponseBody
    @ApiOperation(value = "check whether email has already been registered by other public or charity.",
            consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "email", value = "email address", dataTypeClass = String.class,
            paramType = "query", required = true)
    @GetMapping(path = "/checkEmail")
    public ReturnStatus checkEmail(@PathParam("email") String email) {
        return charityUserService.checkEmail(email);
    }


    @ResponseBody
    @ApiOperation(value = "check if email exist and does email match password.",
            consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "email", value = "email address", dataTypeClass = String.class,
                    paramType = "query", required = true),
            @ApiImplicitParam(name = "password", value = "password of user", dataTypeClass = String.class,
                    paramType = "query", required = true)
    })
    @PostMapping(path = "/signIn")
    public ReturnStatus signIn(@RequestParam("email") String email, @RequestParam("password") String password) {
        try {
            return charityUserService.signIn(email, password);
        } catch (SystemInternalException e) {
            return internalErrorStatus;
        }
    }
}
