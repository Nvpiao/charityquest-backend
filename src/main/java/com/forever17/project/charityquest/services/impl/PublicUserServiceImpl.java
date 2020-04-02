package com.forever17.project.charityquest.services.impl;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.forever17.project.charityquest.constants.CharityCodes;
import com.forever17.project.charityquest.constants.CharityConstants;
import com.forever17.project.charityquest.enums.DonationTimes;
import com.forever17.project.charityquest.enums.DonationType;
import com.forever17.project.charityquest.enums.StatusType;
import com.forever17.project.charityquest.exceptions.SystemInternalException;
import com.forever17.project.charityquest.mapper.CharityUserMapper;
import com.forever17.project.charityquest.mapper.DonationMapper;
import com.forever17.project.charityquest.mapper.FundraisingMapper;
import com.forever17.project.charityquest.mapper.PublicUserMapper;
import com.forever17.project.charityquest.pojos.CharityUser;
import com.forever17.project.charityquest.pojos.CharityUserExample;
import com.forever17.project.charityquest.pojos.Donation;
import com.forever17.project.charityquest.pojos.DonationExample;
import com.forever17.project.charityquest.pojos.Fundraising;
import com.forever17.project.charityquest.pojos.FundraisingExample;
import com.forever17.project.charityquest.pojos.Message;
import com.forever17.project.charityquest.pojos.PublicUser;
import com.forever17.project.charityquest.pojos.PublicUserExample;
import com.forever17.project.charityquest.pojos.entity.DonationCharityDetails;
import com.forever17.project.charityquest.pojos.entity.DonationFundraisingCharityDetails;
import com.forever17.project.charityquest.pojos.entity.FundraisingCharityDetails;
import com.forever17.project.charityquest.pojos.entity.ReturnStatus;
import com.forever17.project.charityquest.services.PublicUserService;
import com.forever17.project.charityquest.tools.paypal.service.PaypalService;
import com.forever17.project.charityquest.utils.EscapeUtils;
import com.forever17.project.charityquest.utils.MD5Util;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Implementation of PublicUserService
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 15 Feb 2020
 * @since 1.0
 */
@Slf4j
@Service
public class PublicUserServiceImpl implements PublicUserService {

    /**
     * http session
     */
    private final HttpSession httpSession;

    /**
     * paypal service
     */
    private final PaypalService paypalService;

    /**
     * client of amazon sns
     */
    private final AmazonSNS amazonSNSClient;

    /**
     * public user mapper
     */
    private final PublicUserMapper publicUserMapper;

    /**
     * charity user mapper
     */
    private final CharityUserMapper charityUserMapper;

    /**
     * fundraising mapper
     */
    private final FundraisingMapper fundraisingMapper;

    /**
     * donation mapper
     */
    private final DonationMapper donationMapper;

    /**
     * Example of PublicUser class
     */
    private PublicUserExample publicUserExample;

    /**
     * Example of CharityUser class
     */
    private CharityUserExample charityUserExample;

    /**
     * Example of Fundraising class
     */
    private FundraisingExample fundraisingExample;

    /**
     * Example of Donation class
     */
    private DonationExample donationExample;

    {
        // static initialization
        publicUserExample = new PublicUserExample();
        charityUserExample = new CharityUserExample();
        fundraisingExample = new FundraisingExample();
        donationExample = new DonationExample();
    }

    @Autowired
    public PublicUserServiceImpl(HttpSession httpSession, PaypalService paypalService,
                                 AmazonSNSClient amazonSNSClient, PublicUserMapper publicUserMapper,
                                 CharityUserMapper charityUserMapper, FundraisingMapper fundraisingMapper,
                                 DonationMapper donationMapper) {
        this.httpSession = httpSession;
        this.paypalService = paypalService;
        this.amazonSNSClient = amazonSNSClient;
        this.publicUserMapper = publicUserMapper;
        this.charityUserMapper = charityUserMapper;
        this.fundraisingMapper = fundraisingMapper;
        this.donationMapper = donationMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReturnStatus addUser(PublicUser publicUser) throws SystemInternalException {
        String userId = UUID.randomUUID().toString();
        String password = publicUser.getPassword();

        String md5Password = convertPasswordToMd5(password);
        // set uuid
        publicUser.setId(userId);
        // set md5 password
        publicUser.setPassword(md5Password);

        // email check
        ReturnStatus emailStatus = checkEmail(publicUser.getEmail());
        if (emailStatus.getStatusType().equals(StatusType.WARN)) {
            return emailStatus;
        }

        // insert into database
        publicUserMapper.insert(publicUser);

        // return success result status
        return new ReturnStatus(CharityConstants.RETURN_PUBLIC_USER_ADDED);
    }

    @Override
    public ReturnStatus checkEmail(String email) {
        // query public user by email address
        publicUserExample.clear();
        publicUserExample.createCriteria().andEmailEqualTo(email);
        List<PublicUser> publicUsers = publicUserMapper.selectByExample(publicUserExample);

        // query charity user by email address
        charityUserExample.clear();
        charityUserExample.createCriteria().andEmailEqualTo(email);
        List<CharityUser> charityUsers = charityUserMapper.selectByExample(charityUserExample);

        // judge
        if (publicUsers.isEmpty() && charityUsers.isEmpty()) {
            return new ReturnStatus(CharityConstants.RETURN_EMAIL_CAN_BE_USED);
        } else {
            log.warn(CharityConstants.RETURN_EMAIL_CAN_NOT_BE_USED);
            return new ReturnStatus(CharityConstants.RETURN_EMAIL_CAN_NOT_BE_USED,
                    CharityCodes.EMAIL_ADDRESS_CAN_NOT_BE_USED, StatusType.WARN);
        }
    }

    @Override
    public ReturnStatus signIn(String email, String password) throws SystemInternalException {
        // query public user by email address
        publicUserExample.clear();
        publicUserExample.createCriteria().andEmailEqualTo(email);
        List<PublicUser> publicUsers = publicUserMapper.selectByExample(publicUserExample);

        if (!publicUsers.isEmpty()) {
            assert publicUsers.size() == 1;

            // get this user
            PublicUser publicUser = publicUsers.get(0);
            String userPassword = publicUser.getPassword();
            String userId = publicUser.getId();

            // md5 incoming password
            String md5Password = convertPasswordToMd5(password);

            if (md5Password.equals(userPassword)) {
                // correct && set to session
                httpSession.setAttribute(CharityConstants.HEADER_REQUEST_TOKEN, userId);
                log.info(String.format(CharityConstants.LOG_USER_LOGIN_SUCCESS, email));
                // success return
                publicUser.setPassword(null);
                return new ReturnStatus(CharityConstants.RETURN_USER_LOGIN_SUCCESS, publicUser);
            } else {
                // incorrect user password
                log.error(String.format(CharityConstants.LOG_INCORRECT_PASSWORD, email));
                return new ReturnStatus(CharityConstants.RETURN_EMAIL_OR_PASSWORD_ERROR,
                        CharityCodes.LOGIN_PASSWORD_ERROR, StatusType.FAIL);
            }
        } else {
            // user does not exist
            log.error(String.format(CharityConstants.LOG_USER_DOES_NOT_EXIST_EMAIL, email));
            return new ReturnStatus(CharityConstants.RETURN_EMAIL_OR_PASSWORD_ERROR,
                    CharityCodes.LOGIN_EMAIL_DOES_NOT_EXIST, StatusType.FAIL);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReturnStatus changePassword(String publicId, String password) throws SystemInternalException {
        // query public user by id of public user
        PublicUser publicUser = publicUserMapper.selectByPrimaryKey(publicId);

        if (!Objects.isNull(publicUser)) {
            String md5Password = convertPasswordToMd5(password);

            if (md5Password.equals(publicUser.getPassword())) {
                // password can not be the same as previous.
                log.warn(String.format(CharityConstants.LOG_CHANGE_PASSWORD_DUPLICATE, publicId));
                return new ReturnStatus(CharityConstants.RETURN_PASSWORD_DUPLICATED_ERROR,
                        CharityCodes.CHANGE_PASSWORD_DUPLICATE, StatusType.WARN);
            }

            publicUserMapper.updateByPrimaryKeySelective(new PublicUser(publicId, md5Password));
            // success return
            return new ReturnStatus(CharityConstants.RETURN_CHANGE_PASSWORD_SUCCESS);

        } else {
            // user does not exist
            log.error(String.format(CharityConstants.LOG_USER_DOES_NOT_EXIST_ID, publicId));
            return new ReturnStatus(CharityConstants.RETURN_USER_DOES_NOT_EXIST_ERROR,
                    CharityCodes.LOGIN_USER_DOES_NOT_EXIST, StatusType.FAIL);
        }
    }

    @Override
    public ReturnStatus showProfile(String id) {
        PublicUser publicUser = publicUserMapper.selectByPrimaryKey(id);
        if (Objects.isNull(publicUser)) {
            // user does not exist
            log.error(String.format(CharityConstants.LOG_USER_DOES_NOT_EXIST_ID, id));
            return new ReturnStatus(CharityConstants.RETURN_USER_DOES_NOT_EXIST_ERROR,
                    CharityCodes.LOGIN_USER_DOES_NOT_EXIST, StatusType.FAIL);
        }
        // hide password
        publicUser.setPassword(null);
        // return success
        return new ReturnStatus(CharityConstants.RETURN_USER_INFO_GET_SUCCESS, publicUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReturnStatus updateUser(PublicUser publicUser) {
        // set password && location null
        publicUser.setPassword(null);
        publicUser.setLocation(null);

        publicUser.setEmail(null);
        // update profile
        publicUserMapper.updateByPrimaryKeySelective(publicUser);
        return new ReturnStatus(CharityConstants.RETURN_USER_INFO_UPDATE_SUCCESS);
    }

    @Override
    public ReturnStatus showCharityList(String search, int pageNum, int pageSize) {

        // escape
        search = EscapeUtils.escapeMysql(search.toLowerCase().trim());

        // search criteria
        search = CharityConstants.SEARCH_WILD_CARD + search + CharityConstants.SEARCH_WILD_CARD;

        // charity example
        charityUserExample.clear();
        charityUserExample.createCriteria()
                .andNameLike(search);
        charityUserExample.setOrderByClause(CharityConstants.SQL_ORDER_CHARITY_NAME_DESC);

        // page helper
        Page<Message> page = PageHelper.startPage(pageNum, pageSize);
        List<CharityUser> charityUsers = charityUserMapper.selectByExample(charityUserExample);

        // assembly data
        Map<String, Object> dataMap = Maps.newHashMap();
        dataMap.put(CharityConstants.DATA_PAGE_HELPER_TOTAL_NUMS, page.getTotal());
        dataMap.put(CharityConstants.DATA_PAGE_HELPER_TOTAL_PAGES, page.getPages());
        dataMap.put(CharityConstants.DATA_PAGE_HELPER_NOW_PAGE, page.getPageNum());
        dataMap.put(CharityConstants.DATA_PAGE_HELPER_DATA, charityUsers);

        // return result
        return new ReturnStatus(CharityConstants.RETURN_CHARITY_LIST_GET_SUCCESS, dataMap);
    }

    @Override
    public ReturnStatus checkLink(String link) {
        // null check
        if (StringUtils.isBlank(link)) {
            return new ReturnStatus(CharityConstants.RETURN_URL_CAN_NOT_BLANK,
                    CharityCodes.PROPERTY_CAN_NOT_BE_BLANK, StatusType.FAIL);
        }

        fundraisingExample.clear();
        fundraisingExample.createCriteria()
                .andUrlEqualTo(link);
        List<Fundraising> fundraisings = fundraisingMapper.selectByExample(fundraisingExample);

        if (fundraisings.isEmpty()) {
            return new ReturnStatus(CharityConstants.RETURN_URL_CAN_BE_USED);
        } else {
            log.error(String.format(CharityConstants.LOG_FUNDRAISING_URL_CAN_NOT_BE_USED, link));
            return new ReturnStatus(CharityConstants.RETURN_URL_CAN_NOT_BE_USED,
                    CharityCodes.URL_CAN_NOT_BE_USED, StatusType.FAIL);
        }
    }

    @Override
    public ReturnStatus getFundraisingByLink(String link) {
        return getFundraising(link, CharityConstants.FUNDRAISING_LINK);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReturnStatus createFundraising(Fundraising fundraising) {
        // check link first
        ReturnStatus linkStatus = checkLink(fundraising.getUrl());
        if (linkStatus.getStatusType() == StatusType.FAIL) {
            return linkStatus;
        }

        // check charity
        CharityUser charityUser = charityUserMapper.selectByPrimaryKey(fundraising.getCharityId());
        if (Objects.isNull(charityUser)) {
            log.error(String.format(CharityConstants.LOG_CHARITY_DOES_NOT_EXIST, fundraising.getCharityId()));
            return new ReturnStatus(CharityConstants.RETURN_CHARITY_DOES_NOT_EXIST,
                    CharityCodes.CHARITY_DOES_NOT_EXIST, StatusType.FAIL);
        }

        // check duration
        long days = Duration.between(fundraising.getStartTime(), fundraising.getEndTime()).toDays();
        if (days < 1) {
            log.error(String.format(CharityConstants.LOG_FUNDRAISING_LAST_LESS_THAN_ONE_DAY, fundraising.getCharityId()));
            return new ReturnStatus(CharityConstants.RETURN_FUNDRAISING_LAST_LESS_THAN_ONE_DAY,
                    CharityCodes.FUNDRAISING_LAST_LESS_THAN_ONE_DAY, StatusType.FAIL);
        }

        // set fundraising id
        fundraising.setId(UUID.randomUUID().toString());
        // insert into database
        fundraisingMapper.insertSelective(fundraising);

        return getFundraisingByLink(fundraising.getUrl());
    }

    @Override
    public ReturnStatus getFundraisingById(String id) {
        return getFundraising(id, CharityConstants.FUNDRAISING_ID);
    }


    @Override
    public ReturnStatus donateThroughPaypal(DonationType donationType, String fundraisingId, String charityId,
                                            String publicId, float money) throws SystemInternalException {
        PublicUser publicUser = publicUserMapper.selectByPrimaryKey(publicId);
        Fundraising fundraising = null;
        if (donationType == DonationType.FUNDRAISING) {
            // check exist
            fundraising = fundraisingMapper.selectByPrimaryKey(fundraisingId);
            if (Objects.isNull(fundraising) || Objects.isNull(publicUser)) {
                log.error(String.format(CharityConstants.LOG_FUNDRAISING_OR_PUBLIC_CAN_NOT_FOUND, fundraisingId, publicId));
                return new ReturnStatus(CharityConstants.RETURN_FUNDRAISING_OR_PUBLIC_CAN_NOT_FOUND,
                        CharityCodes.FUNDRAISING_OR_PUBLIC_CAN_NOT_FOUND, StatusType.FAIL);
            }
        } else if (donationType == DonationType.DONATION) {
            // check exist
            CharityUser charityUser = charityUserMapper.selectByPrimaryKey(charityId);
            if (Objects.isNull(charityUser) || Objects.isNull(publicUser)) {
                log.error(String.format(CharityConstants.LOG_CHARITY_OR_PUBLIC_CAN_NOT_FOUND, charityId, publicId));
                return new ReturnStatus(CharityConstants.RETURN_CHARITY_OR_PUBLIC_CAN_NOT_FOUND,
                        CharityCodes.CHARITY_OR_PUBLIC_CAN_NOT_FOUND, StatusType.FAIL);
            }
        }

        try {
            // create payment
            Payment payment = paypalService.createPayment(donationType, fundraisingId, charityId,
                    publicId, money, Objects.isNull(fundraising) ? null : fundraising.getUrl());
            for (Links links : payment.getLinks()) {
                if (links.getRel().equals(CharityConstants.PAYPAL_APPROVAL_LINK)) {
                    return new ReturnStatus(CharityConstants.RETURN_PAY_REDIRECTION_GET_SUCCESS,
                            ImmutableMap.of(CharityConstants.DATA_PAYPAL_REDIRECT, links.getHref()));
                }
            }
        } catch (PayPalRESTException e) {
            // exception
            log.error(CharityConstants.RETURN_PAY_REDIRECTION_GET_FAILED, e);
            throw new SystemInternalException(CharityConstants.RETURN_PAY_REDIRECTION_GET_FAILED);
        }

        // noting happen
        return new ReturnStatus(CharityConstants.RETURN_PAY_REDIRECTION_GET_FAILED,
                CharityCodes.PAYPAL_PAY_LINK_GET_FAILED, StatusType.FAIL);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReturnStatus executePayment(DonationType donationType, String fundraisingId, String charityId, String publicId,
                                       String paymentId, String payerId, double money) throws SystemInternalException {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if (payment.getState().equals(CharityConstants.PAYPAL_STATUS_APPROVED)) {
                if (donationType == DonationType.FUNDRAISING) {
                    // update money
                    fundraisingMapper.updateMoney(fundraisingId, money);
                }

                // create donation
                Donation donation = Donation.builder()
                        .id(UUID.randomUUID().toString())
                        .publicId(publicId)
                        .type(donationType.name().toLowerCase())
                        .fundraisingId(fundraisingId.equals(CharityConstants.DEFAULT_VALUE_NULL_STRING)
                                ? null : fundraisingId)
                        .charityId(charityId.equals(CharityConstants.DEFAULT_VALUE_NULL_STRING)
                                ? null : charityId)
                        .money(money)
                        .time(LocalDateTime.now())
                        .build();
                // add donation history
                donationMapper.insertSelective(donation);
                if (donationType == DonationType.FUNDRAISING) {
                    // return details of fundraising
                    return getFundraisingById(fundraisingId);
                } else if (donationType == DonationType.DONATION) {
                    // return details of charity
                    return getCharityById(charityId);
                }
            }
        } catch (PayPalRESTException e) {
            // exception
            log.error(CharityConstants.RETURN_PAYMENT_STATUS_FAILED, e);
            throw new SystemInternalException(CharityConstants.RETURN_PAYMENT_STATUS_FAILED);
        }
        // noting happen
        return new ReturnStatus(CharityConstants.RETURN_PAYMENT_STATUS_FAILED,
                CharityCodes.PAYPAL_PAY_STATUS_FAILED, StatusType.FAIL);
    }

    /**
     * get details of charity by id of charity
     *
     * @param charityId id of charity
     * @return instance of ReturnStatus
     */
    private ReturnStatus getCharityById(String charityId) {
        CharityUser charityUser = charityUserMapper.selectByPrimaryKey(charityId);
        return new ReturnStatus(CharityConstants.RETURN_CHARITY_INFO_GET_SUCCESS, charityUser);
    }

    @Override
    public ReturnStatus shareThroughSms(String url, String tel) {
        String message = CharityConstants.SMS_MESSAGE + url;

        Map<String, MessageAttributeValue> smsAttributes = Maps.newHashMap();
        //<set SMS attributes>
        smsAttributes.put(CharityConstants.SMS_ATTRIBUTE_MAX_PRICE, new MessageAttributeValue()
                .withStringValue(CharityConstants.SMS_ATTRIBUTE_MAX_PRICE_VALUE) //Sets the max price to 0.10 USD.
                .withDataType(CharityConstants.SMS_ATTRIBUTE_NUMBER_TYPE));

        // send message
        PublishResult result = amazonSNSClient.publish(new PublishRequest()
                .withMessage(message)
                .withPhoneNumber(tel)
                .withMessageAttributes(smsAttributes));
        int httpStatusCode = result.getSdkHttpMetadata().getHttpStatusCode();
        if (httpStatusCode == 200) {
            return new ReturnStatus(CharityConstants.RETURN_SMS_SENT_SUCCESS);
        } else {
            log.error(String.format(CharityConstants.LOG_SMS_SEND_FAILED, tel));
            return new ReturnStatus(CharityConstants.LOG_MESSAGE_SEND_FAILED,
                    CharityCodes.MESSAGE_SEND_FAILED, StatusType.FAIL);
        }
    }

    @Override
    public ReturnStatus showDonationList(String publicId, int pageNum, int pageSize, String search) {
        // donation example
        donationExample.clear();
        DonationExample.Criteria donationCriteria = donationExample.createCriteria()
                .andTypeEqualTo(DonationType.DONATION.name().toLowerCase())
                .andDonateTypeEqualTo(DonationTimes.ONCE.name().toLowerCase())
                .andPublicIdEqualTo(publicId);
        return getDonation(publicId, pageNum, pageSize, search, donationCriteria);
    }

    @Override
    public ReturnStatus showFundraisingHistory(String publicId, int pageNum, int pageSize, String search) {
        PublicUser publicUser = publicUserMapper.selectByPrimaryKey(publicId);
        if (Objects.isNull(publicUser)) {
            // user does not exist
            log.error(String.format(CharityConstants.LOG_USER_DOES_NOT_EXIST_ID, publicId));
            return new ReturnStatus(CharityConstants.RETURN_USER_DOES_NOT_EXIST_ERROR,
                    CharityCodes.LOGIN_USER_DOES_NOT_EXIST, StatusType.FAIL);
        }

        // escape
        search = EscapeUtils.escapeMysql(search.toLowerCase().trim());
        // search criteria
        search = CharityConstants.SEARCH_WILD_CARD + search + CharityConstants.SEARCH_WILD_CARD;

        // charity example
        charityUserExample.clear();
        charityUserExample.createCriteria().andNameLike(search);
        List<CharityUser> charityUsers = charityUserMapper.selectByExample(charityUserExample);

        if (!charityUsers.isEmpty()) {
            List<String> charityIds = charityUsers.stream()
                    .map(CharityUser::getId)
                    .collect(Collectors.toList());
            // fundraising example
            fundraisingExample.clear();
            fundraisingExample.createCriteria().andCharityIdIn(charityIds);
            List<Fundraising> fundraisings = fundraisingMapper.selectByExample(fundraisingExample);
            if (!fundraisings.isEmpty()) {
                List<String> fundraisingIds = fundraisings.stream()
                        .map(Fundraising::getId)
                        .collect(Collectors.toList());

                // donation example
                donationExample.clear();
                donationExample.createCriteria()
                        .andFundraisingIdIn(fundraisingIds)
                        .andTypeEqualTo(DonationType.FUNDRAISING.name().toLowerCase())
                        .andPublicIdEqualTo(publicId);
                donationExample.setOrderByClause(CharityConstants.SQL_ORDER_DONATION_TIME_DESC);

                // page helper
                Page<Donation> page = PageHelper.startPage(pageNum, pageSize);
                List<Donation> donations = donationMapper.selectByExample(donationExample);
                if (!donations.isEmpty()) {

                    List<DonationFundraisingCharityDetails> fundraisingHistories = Lists.newArrayList();
                    donations.forEach(donation -> {
                        // query fundraising detail
                        ReturnStatus fundraisingRe = getFundraisingById(donation.getFundraisingId());
                        FundraisingCharityDetails fundraisingDetail = (FundraisingCharityDetails) fundraisingRe.getData();

                        // combine to single class
                        DonationFundraisingCharityDetails fundraisingHistory = new DonationFundraisingCharityDetails(fundraisingDetail, donation);
                        fundraisingHistories.add(fundraisingHistory);
                    });

                    // assembly data
                    Map<String, Object> dataMap = Maps.newHashMap();
                    dataMap.put(CharityConstants.DATA_PAGE_HELPER_TOTAL_NUMS, page.getTotal());
                    dataMap.put(CharityConstants.DATA_PAGE_HELPER_TOTAL_PAGES, page.getPages());
                    dataMap.put(CharityConstants.DATA_PAGE_HELPER_NOW_PAGE, page.getPageNum());
                    dataMap.put(CharityConstants.DATA_PAGE_HELPER_DATA, fundraisingHistories);
                    // return result
                    return new ReturnStatus(CharityConstants.RETURN_FUNDRAISING_LIST_GET_SUCCESS, dataMap);
                }

            }
        }
        log.warn(String.format(CharityConstants.LOG_NO_FUNDRAISING_LIST, publicId));
        return new ReturnStatus(CharityConstants.RETURN_NO_FUNDRAISING_LIST,
                CharityCodes.NO_FUNDRAISING_LIST, StatusType.WARN);
    }

    @Override
    public ReturnStatus showRegulationDonation(String publicId, int pageNum, int pageSize, String search) {
        // donation example
        donationExample.clear();
        DonationExample.Criteria donationCriteria = donationExample.createCriteria()
                .andTypeEqualTo(DonationType.DONATION.name().toLowerCase())
                .andDonateTypeNotEqualTo(DonationTimes.ONCE.name().toLowerCase())
                .andPublicIdEqualTo(publicId);
        return getDonation(publicId, pageNum, pageSize, search, donationCriteria);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReturnStatus cancelRegulationDonation(String publicId, String donationId) {
        PublicUser publicUser = publicUserMapper.selectByPrimaryKey(publicId);
        if (Objects.isNull(publicUser)) {
            // user does not exist
            log.error(String.format(CharityConstants.LOG_USER_DOES_NOT_EXIST_ID, publicId));
            return new ReturnStatus(CharityConstants.RETURN_USER_DOES_NOT_EXIST_ERROR,
                    CharityCodes.LOGIN_USER_DOES_NOT_EXIST, StatusType.FAIL);
        }

        Donation donation = donationMapper.selectByPrimaryKey(donationId);
        if (!Objects.isNull(donation)) {
            donation.setStatus(CharityConstants.DONATION_STATUS_CANCELLED);
            donationMapper.updateByPrimaryKeySelective(donation);
            return new ReturnStatus(CharityConstants.RETURN_REGULAR_DONATION_CANCELLED_SUCCESS);
        }

        log.error(String.format(CharityConstants.LOG_DONATION_NOT_EXIST, donationId));
        return new ReturnStatus(CharityConstants.RETURN_DONATION_NOT_EXIST,
                CharityCodes.DONATION_NOT_EXIST, StatusType.FAIL);
    }

    private ReturnStatus getDonation(String publicId, int pageNum, int pageSize, String search,
                                     DonationExample.Criteria donationCriteria) {
        PublicUser publicUser = publicUserMapper.selectByPrimaryKey(publicId);
        if (Objects.isNull(publicUser)) {
            // user does not exist
            log.error(String.format(CharityConstants.LOG_USER_DOES_NOT_EXIST_ID, publicId));
            return new ReturnStatus(CharityConstants.RETURN_USER_DOES_NOT_EXIST_ERROR,
                    CharityCodes.LOGIN_USER_DOES_NOT_EXIST, StatusType.FAIL);
        }

        // escape
        search = EscapeUtils.escapeMysql(search.toLowerCase().trim());
        // search criteria
        search = CharityConstants.SEARCH_WILD_CARD + search + CharityConstants.SEARCH_WILD_CARD;

        // charity example
        charityUserExample.clear();
        charityUserExample.createCriteria().andNameLike(search);
        List<CharityUser> charityUsers = charityUserMapper.selectByExample(charityUserExample);

        if (!charityUsers.isEmpty()) {
            List<String> charityIds = charityUsers.stream()
                    .map(CharityUser::getId)
                    .collect(Collectors.toList());

            donationCriteria.andCharityIdIn(charityIds);
            donationExample.setOrderByClause(CharityConstants.SQL_ORDER_DONATION_TIME_DESC);

            // page helper
            Page<Donation> page = PageHelper.startPage(pageNum, pageSize);
            List<Donation> donations = donationMapper.selectByExample(donationExample);
            if (!donations.isEmpty()) {

                List<DonationCharityDetails> donationCharityDetails = Lists.newArrayList();
                donations.forEach(donation -> {
                    ReturnStatus charityRe = getCharityById(donation.getCharityId());
                    CharityUser charityUser = (CharityUser) charityRe.getData();
                    DonationCharityDetails donationCharityDetail = new DonationCharityDetails(donation, charityUser);
                    donationCharityDetails.add(donationCharityDetail);
                });

                // assembly data
                Map<String, Object> dataMap = Maps.newHashMap();
                dataMap.put(CharityConstants.DATA_PAGE_HELPER_TOTAL_NUMS, page.getTotal());
                dataMap.put(CharityConstants.DATA_PAGE_HELPER_TOTAL_PAGES, page.getPages());
                dataMap.put(CharityConstants.DATA_PAGE_HELPER_NOW_PAGE, page.getPageNum());
                dataMap.put(CharityConstants.DATA_PAGE_HELPER_DATA, donationCharityDetails);

                // return result
                return new ReturnStatus(CharityConstants.RETURN_DONATION_LIST_GET_SUCCESS, dataMap);
            }
        }
        log.warn(String.format(CharityConstants.LOG_NO_DONATION_LIST, publicId));
        return new ReturnStatus(CharityConstants.RETURN_NO_DONATION_LIST,
                CharityCodes.NO_DONATION_LIST, StatusType.WARN);
    }

    /**
     * get fundraising details by id or link
     *
     * @param data id or link
     * @param type type
     * @return instance of ReturnStatus
     */
    private ReturnStatus getFundraising(String data, String type) {
        // null check
        if (type.equals(CharityConstants.FUNDRAISING_LINK) && StringUtils.isBlank(data)) {
            return new ReturnStatus(CharityConstants.RETURN_URL_CAN_NOT_BLANK,
                    CharityCodes.PROPERTY_CAN_NOT_BE_BLANK, StatusType.FAIL);
        }
        // null check
        if (type.equals(CharityConstants.FUNDRAISING_ID) && StringUtils.isBlank(data)) {
            return new ReturnStatus(CharityConstants.RETURN_ID_CAN_NOT_BLANK,
                    CharityCodes.PROPERTY_CAN_NOT_BE_BLANK, StatusType.FAIL);
        }

        fundraisingExample.clear();
        FundraisingExample.Criteria criteria = fundraisingExample.createCriteria();
        // link
        if (type.equals(CharityConstants.FUNDRAISING_LINK)) {
            criteria.andUrlEqualTo(data);
        }
        // id
        if (type.equals(CharityConstants.FUNDRAISING_ID)) {
            criteria.andIdEqualTo(data);
        }

        List<Fundraising> fundraisings = fundraisingMapper.selectByExample(fundraisingExample);
        if (!fundraisings.isEmpty()) {
            // at most one
            assert fundraisings.size() == 1;
            Fundraising fundraising = fundraisings.get(0);
            CharityUser charityUser = charityUserMapper.selectByPrimaryKey(fundraising.getCharityId());

            assert charityUser != null;
            return new ReturnStatus(CharityConstants.RETURN_FUNDRAISING_DETAILS_GET_SUCCESS,
                    new FundraisingCharityDetails(fundraising, charityUser));
        } else {
            log.error(String.format(CharityConstants.LOG_FUNDRAISING_DOES_NOT_EXIST, data));
            return new ReturnStatus(CharityConstants.RETURN_FUNDRAISING_DOES_NOT_EXIST,
                    CharityCodes.FUNDRAISING_DOES_NOT_EXIST, StatusType.FAIL);
        }
    }

    /**
     * convert password to md5 representation
     *
     * @param password password
     * @return encrypt password with md5
     * @throws SystemInternalException system internal exception
     */
    private String convertPasswordToMd5(String password) throws SystemInternalException {
        // md5 incoming password
        String md5Password;
        try {
            // convert password into md5 code
            md5Password = MD5Util.md5(password);
        } catch (NoSuchAlgorithmException e) {
            log.error(CharityConstants.NO_SUCH_ALGORITHM_ERROR, e);
            throw new SystemInternalException(CharityConstants.NO_SUCH_ALGORITHM_ERROR);
        }
        return md5Password;
    }
}
