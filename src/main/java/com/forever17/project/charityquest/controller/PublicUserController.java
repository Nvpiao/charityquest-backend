package com.forever17.project.charityquest.controller;

import com.forever17.project.charityquest.exceptions.SystemInternalException;
import com.forever17.project.charityquest.pojos.PublicUser;
import com.forever17.project.charityquest.services.PublicUserService;
import com.forever17.project.charityquest.utils.ReturnStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping(path = "/publicUser", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class PublicUserController {

    @Autowired
    public ReturnStatus internalErrorStatus;

    @Autowired
    PublicUserService publicUserService;

    @ResponseBody
    @PostMapping(path = "/register")
    public ReturnStatus register(@Valid @RequestBody PublicUser publicUser) {
        try {
            return publicUserService.addUser(publicUser);
        } catch (SystemInternalException e) {
            return internalErrorStatus;
        }
    }
}
