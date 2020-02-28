package com.forever17.project.charityquest.controller;

import com.forever17.project.charityquest.exceptions.SystemInternalException;
import com.forever17.project.charityquest.pojos.PublicUser;
import com.forever17.project.charityquest.pojos.entity.ReturnStatus;
import com.forever17.project.charityquest.services.PublicUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

@Slf4j
@RestController
@Api(tags = "PublicUser")
@RequestMapping(path = "/publicUser", consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class PublicUserController {

    @Autowired
    public ReturnStatus internalErrorStatus;

    @Autowired
    PublicUserService publicUserService;

    @ResponseBody
    @ApiOperation("add new public user")
    @PostMapping(path = "/register")
    public ReturnStatus register(@Valid @RequestBody PublicUser publicUser) {
        try {
            return publicUserService.addUser(publicUser);
        } catch (SystemInternalException e) {
            return internalErrorStatus;
        }
    }

    @ResponseBody
    @ApiOperation(value = "check whether email has already been registered by other public or charity.",
            consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "email", value = "email address", dataTypeClass = String.class,
            paramType = "query", required = true)
    @GetMapping(path = "/checkEmail", consumes = MediaType.TEXT_PLAIN_VALUE)
    public ReturnStatus checkEmail(@PathParam("email") String email) {
        return publicUserService.checkEmail(email);
    }
}
