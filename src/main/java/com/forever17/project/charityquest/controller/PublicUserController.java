package com.forever17.project.charityquest.controller;

import com.forever17.project.charityquest.aop.annotation.LoginCheck;
import com.forever17.project.charityquest.enums.DonationType;
import com.forever17.project.charityquest.exceptions.SystemInternalException;
import com.forever17.project.charityquest.pojos.Fundraising;
import com.forever17.project.charityquest.pojos.PublicUser;
import com.forever17.project.charityquest.pojos.entity.ReturnStatus;
import com.forever17.project.charityquest.services.PublicUserService;
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

/**
 * Controller of Public user
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 21 Feb 2020
 * @since 1.0
 */
@Slf4j
@CrossOrigin(allowCredentials = "true")
@RestController
@Api(tags = "PublicUser")
@RequestMapping(path = "/publicUser", consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class PublicUserController {

    private final ReturnStatus internalErrorStatus;

    private final PublicUserService publicUserService;

    @Autowired
    public PublicUserController(ReturnStatus internalErrorStatus, PublicUserService publicUserService) {
        this.internalErrorStatus = internalErrorStatus;
        this.publicUserService = publicUserService;
    }

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
    @ApiImplicitParam(name = "email", value = "email address",
            dataTypeClass = String.class, paramType = "query", required = true)
    @GetMapping(path = "/checkEmail")
    public ReturnStatus checkEmail(@RequestParam("email") String email) {
        return publicUserService.checkEmail(email);
    }


    @ResponseBody
    @ApiOperation(value = "check if email exist and does email match password.",
            consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "email", value = "email address",
                    dataTypeClass = String.class, paramType = "query", required = true),
            @ApiImplicitParam(name = "password", value = "password of user",
                    dataTypeClass = String.class, paramType = "query", required = true)
    })
    @PostMapping(path = "/signIn")
    public ReturnStatus signIn(@RequestParam("email") String email,
                               @RequestParam("password") String password) {
        try {
            return publicUserService.signIn(email, password);
        } catch (SystemInternalException e) {
            return internalErrorStatus;
        }
    }

    @LoginCheck
    @ResponseBody
    @ApiOperation(value = "change password of public user.",
            consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "publicId", value = "id of public user",
                    dataTypeClass = String.class, paramType = "query", required = true),
            @ApiImplicitParam(name = "password", value = "password of user",
                    dataTypeClass = String.class, paramType = "query", required = true)
    })
    @PostMapping(path = "changePassword")
    public ReturnStatus changePassword(@RequestParam("publicId") String publicId,
                                       @RequestParam("password") String password) {
        try {
            return publicUserService.changePassword(publicId, password);
        } catch (SystemInternalException e) {
            return internalErrorStatus;
        }
    }

    @LoginCheck
    @ResponseBody
    @ApiOperation(value = "show the profile of public user.",
            consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "publicId", value = "id of public user",
            dataTypeClass = String.class, paramType = "query", required = true)
    @GetMapping(path = "/showProfile")
    public ReturnStatus showProfile(@RequestParam("publicId") String id) {
        return publicUserService.showProfile(id);
    }

    @LoginCheck
    @ResponseBody
    @ApiOperation("update profile of public user")
    @PostMapping(path = "/update")
    public ReturnStatus update(@RequestBody PublicUser publicUser) {
        return publicUserService.updateUser(publicUser);
    }

    @ResponseBody
    @ApiOperation("get list of charity with searching criteria")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "search", value = "criteria of searching",
                    dataTypeClass = String.class, paramType = "query", required = true),
            @ApiImplicitParam(name = "pageNum", value = "number of page",
                    dataTypeClass = Integer.class, paramType = "query", required = true),
            @ApiImplicitParam(name = "pageSize", value = "size of a page",
                    dataTypeClass = Integer.class, paramType = "query", required = true)
    })
    @GetMapping(path = "/showCharityList")
    public ReturnStatus showCharityList(@RequestParam("search") String search,
                                        @RequestParam("pageNum") int pageNum,
                                        @RequestParam("pageSize") int pageSize) {
        return publicUserService.showCharityList(search, pageNum, pageSize);
    }

    @ResponseBody
    @ApiOperation(value = "check if link already been used.",
            consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "link", value = "link of fundraising",
            dataTypeClass = String.class, paramType = "query", required = true)
    @GetMapping(path = "/checkLink")
    public ReturnStatus checkLink(@RequestParam("link") String link) {
        return publicUserService.checkLink(link);
    }

    @ResponseBody
    @ApiOperation(value = "get fundraising details by link.",
            consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "link", value = "link of fundraising",
            dataTypeClass = String.class, paramType = "query", required = true)
    @GetMapping(path = "/getFundraisingByLink")
    public ReturnStatus getFundraisingByLink(@RequestParam("link") String link) {
        return publicUserService.getFundraisingByLink(link);
    }

    @LoginCheck
    @ResponseBody
    @ApiOperation("create a fundraising")
    @PostMapping(path = "/createFundraising")
    public ReturnStatus createFundraising(@Valid @RequestBody Fundraising fundraising) {
        return publicUserService.createFundraising(fundraising);
    }

    @ResponseBody
    @ApiOperation(value = "get fundraising details by id.",
            consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "fundraisingId", value = "id of fundraising",
            dataTypeClass = String.class, paramType = "query", required = true)
    @GetMapping(path = "/getFundraisingDetail")
    public ReturnStatus getFundraisingDetail(@RequestParam("fundraisingId") String id) {
        return publicUserService.getFundraisingById(id);
    }

    @LoginCheck
    @ResponseBody
    @ApiOperation(value = "donate through paypal.",
            consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "flag", value = "flag of donation",
                    dataTypeClass = String.class, paramType = "query", required = true),
            @ApiImplicitParam(name = "fundraisingId", value = "id of fundraising",
                    dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "charityId", value = "id of charity",
                    dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "publicId", value = "id of public",
                    dataTypeClass = String.class, paramType = "query", required = true),
            @ApiImplicitParam(name = "money", value = "money of donation",
                    dataTypeClass = Integer.class, paramType = "query", required = true)
    })
    @GetMapping(path = "/donateThroughPaypal")
    public ReturnStatus donateThroughPaypal(@RequestParam("flag") String flag,
                                            @RequestParam(value = "fundraisingId", required = false, defaultValue = "null") String fundraisingId,
                                            @RequestParam(value = "charityId", required = false, defaultValue = "null") String charityId,
                                            @RequestParam("publicId") String publicId,
                                            @RequestParam("money") float money) {
        try {
            return publicUserService.donateThroughPaypal(DonationType.valueOf(flag.toUpperCase()),
                    fundraisingId, charityId, publicId, money);
        } catch (SystemInternalException e) {
            return internalErrorStatus;
        }
    }

    @LoginCheck
    @ResponseBody
    @ApiOperation(value = "finish transaction through paypal.",
            consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "flag", value = "flag of donation",
                    dataTypeClass = String.class, paramType = "query", required = true),
            @ApiImplicitParam(name = "fundraisingId", value = "id of fundraising",
                    dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "charityId", value = "id of charity",
                    dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "publicId", value = "id of public",
                    dataTypeClass = String.class, paramType = "query", required = true),
            @ApiImplicitParam(name = "paymentId", value = "id of payment",
                    dataTypeClass = String.class, paramType = "query", required = true),
            @ApiImplicitParam(name = "payerId", value = "id of payer",
                    dataTypeClass = String.class, paramType = "query", required = true),
            @ApiImplicitParam(name = "money", value = "money of donation",
                    dataTypeClass = Integer.class, paramType = "query", required = true)
    })
    @GetMapping(path = "/finishPaypalTrans")
    public ReturnStatus finishPaypalTrans(@RequestParam("flag") String flag,
                                          @RequestParam(value = "fundraisingId", required = false, defaultValue = "null") String fundraisingId,
                                          @RequestParam(value = "charityId", required = false, defaultValue = "null") String charityId,
                                          @RequestParam("publicId") String publicId,
                                          @RequestParam("paymentId") String paymentId,
                                          @RequestParam("PayerID") String payerId,
                                          @RequestParam("money") double money) {
        try {
            return publicUserService.executePayment(DonationType.valueOf(flag.toUpperCase()), fundraisingId, charityId,
                    publicId, paymentId, payerId, money);
        } catch (SystemInternalException e) {
            return internalErrorStatus;
        }
    }

    @LoginCheck
    @ResponseBody
    @ApiOperation(value = "share fundraising through sms.",
            consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "url", value = "url of fundraising",
                    dataTypeClass = String.class, paramType = "query", required = true),
            @ApiImplicitParam(name = "tel", value = "tel of a user",
                    dataTypeClass = String.class, paramType = "query", required = true)
    })
    @GetMapping(path = "/shareThroughSms")
    public ReturnStatus shareThroughSms(@RequestParam("url") String url,
                                        @RequestParam("tel") String tel) {
        return publicUserService.shareThroughSms(url, tel);
    }

    @LoginCheck
    @ResponseBody
    @ApiOperation("get list of donation history for once donation")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "publicId", value = "id of public",
                    dataTypeClass = String.class, paramType = "query", required = true),
            @ApiImplicitParam(name = "pageNum", value = "number of page",
                    dataTypeClass = Integer.class, paramType = "query", required = true),
            @ApiImplicitParam(name = "pageSize", value = "size of a page",
                    dataTypeClass = Integer.class, paramType = "query", required = true),
            @ApiImplicitParam(name = "search", value = "criteria of searching",
                    dataTypeClass = String.class, paramType = "query", required = true)
    })
    @GetMapping(path = "/showDonationList")
    public ReturnStatus showDonationList(@RequestParam("publicId") String publicId,
                                         @RequestParam("search") String search,
                                         @RequestParam("pageNum") int pageNum,
                                         @RequestParam("pageSize") int pageSize) {
        return publicUserService.showDonationList(publicId, pageNum, pageSize, search);
    }

    @LoginCheck
    @ResponseBody
    @ApiOperation("get list of fundraising history")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "publicId", value = "id of public",
                    dataTypeClass = String.class, paramType = "query", required = true),
            @ApiImplicitParam(name = "pageNum", value = "number of page",
                    dataTypeClass = Integer.class, paramType = "query", required = true),
            @ApiImplicitParam(name = "pageSize", value = "size of a page",
                    dataTypeClass = Integer.class, paramType = "query", required = true),
            @ApiImplicitParam(name = "search", value = "criteria of searching",
                    dataTypeClass = String.class, paramType = "query", required = true)
    })
    @GetMapping(path = "/showFundraisingHistory")
    public ReturnStatus showFundraisingHistory(@RequestParam("publicId") String publicId,
                                               @RequestParam("search") String search,
                                               @RequestParam("pageNum") int pageNum,
                                               @RequestParam("pageSize") int pageSize) {
        return publicUserService.showFundraisingHistory(publicId, pageNum, pageSize, search);
    }


    @LoginCheck
    @ResponseBody
    @ApiOperation("get list of regulation donation history")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "publicId", value = "id of public",
                    dataTypeClass = String.class, paramType = "query", required = true),
            @ApiImplicitParam(name = "pageNum", value = "number of page",
                    dataTypeClass = Integer.class, paramType = "query", required = true),
            @ApiImplicitParam(name = "pageSize", value = "size of a page",
                    dataTypeClass = Integer.class, paramType = "query", required = true),
            @ApiImplicitParam(name = "search", value = "criteria of searching",
                    dataTypeClass = String.class, paramType = "query", required = true)
    })
    @GetMapping(path = "/showRegulationDonation")
    public ReturnStatus showRegulationDonation(@RequestParam("publicId") String publicId,
                                               @RequestParam("search") String search,
                                               @RequestParam("pageNum") int pageNum,
                                               @RequestParam("pageSize") int pageSize) {
        return publicUserService.showRegulationDonation(publicId, pageNum, pageSize, search);
    }
}
