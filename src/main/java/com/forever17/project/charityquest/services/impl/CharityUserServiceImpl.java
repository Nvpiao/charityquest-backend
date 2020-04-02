package com.forever17.project.charityquest.services.impl;

import com.forever17.project.charityquest.constants.CharityCodes;
import com.forever17.project.charityquest.constants.CharityConstants;
import com.forever17.project.charityquest.enums.MessageType;
import com.forever17.project.charityquest.enums.StatusType;
import com.forever17.project.charityquest.exceptions.SystemInternalException;
import com.forever17.project.charityquest.mapper.CharityUserMapper;
import com.forever17.project.charityquest.mapper.DonationMapper;
import com.forever17.project.charityquest.mapper.FundraisingMapper;
import com.forever17.project.charityquest.mapper.MessageMapper;
import com.forever17.project.charityquest.mapper.PublicUserMapper;
import com.forever17.project.charityquest.pojos.CharityUser;
import com.forever17.project.charityquest.pojos.CharityUserExample;
import com.forever17.project.charityquest.pojos.Donation;
import com.forever17.project.charityquest.pojos.DonationExample;
import com.forever17.project.charityquest.pojos.Fundraising;
import com.forever17.project.charityquest.pojos.FundraisingExample;
import com.forever17.project.charityquest.pojos.Message;
import com.forever17.project.charityquest.pojos.MessageExample;
import com.forever17.project.charityquest.pojos.PublicUser;
import com.forever17.project.charityquest.pojos.PublicUserExample;
import com.forever17.project.charityquest.pojos.entity.LocationTimes;
import com.forever17.project.charityquest.pojos.entity.ReturnStatus;
import com.forever17.project.charityquest.services.CharityUserService;
import com.forever17.project.charityquest.utils.MD5Util;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Implementation of CharityUserService
 *
 * @author Yuhao Hu (Yhu744@sheffield.ac.uk)
 * @version 1.0
 * @date 10 Mar 2020
 * @since 1.0
 */
@Slf4j
@Service
public class CharityUserServiceImpl implements CharityUserService {

    /**
     * http session
     */
    private final HttpSession httpSession;

    /**
     * java mail sender
     */
    private final JavaMailSender javaMailSender;

    /**
     * public user mapper
     */
    private final PublicUserMapper publicUserMapper;

    /**
     * charity user mapper
     */
    private final CharityUserMapper charityUserMapper;

    /**
     * message mapper
     */
    private final MessageMapper messageMapper;

    /**
     * donation mapper
     */
    private final DonationMapper donationMapper;

    /**
     * fundraising mapper
     */
    private final FundraisingMapper fundraisingMapper;

    /**
     * Example of PublicUser class
     */
    private CharityUserExample charityUserExample;

    /**
     * Example of PublicUser class
     */
    private PublicUserExample publicUserExample;

    /**
     * Example of Message
     */
    private MessageExample messageExample;

    /**
     * Example of Donation
     */
    private DonationExample donationExample;

    /**
     * Example of Fundraising
     */
    private FundraisingExample fundraisingExample;

    {
        // initialization
        publicUserExample = new PublicUserExample();
        charityUserExample = new CharityUserExample();
        messageExample = new MessageExample();
        donationExample = new DonationExample();
        fundraisingExample = new FundraisingExample();
    }

    @Autowired
    public CharityUserServiceImpl(HttpSession httpSession, JavaMailSender javaMailSender,
                                  PublicUserMapper publicUserMapper, CharityUserMapper charityUserMapper,
                                  MessageMapper messageMapper, DonationMapper donationMapper,
                                  FundraisingMapper fundraisingMapper) {
        this.httpSession = httpSession;
        this.javaMailSender = javaMailSender;
        this.publicUserMapper = publicUserMapper;
        this.charityUserMapper = charityUserMapper;
        this.messageMapper = messageMapper;
        this.donationMapper = donationMapper;
        this.fundraisingMapper = fundraisingMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReturnStatus addUser(CharityUser charityUser) throws SystemInternalException {
        String userId = UUID.randomUUID().toString();
        String password = charityUser.getPassword();

        String md5Password = convertPasswordToMd5(password);
        // set uuid
        charityUser.setId(userId);
        // set md5 password
        charityUser.setPassword(md5Password);

        // email check
        ReturnStatus emailStatus = checkEmail(charityUser.getEmail());
        if (emailStatus.getStatusType().equals(StatusType.WARN)) {
            return emailStatus;
        }

        // insert into database
        charityUserMapper.insert(charityUser);

        // return success result status
        return new ReturnStatus(CharityConstants.RETURN_CHARITY_USER_ADDED);
    }

    @Override
    public ReturnStatus checkEmail(String email) {
        // query charity user by email address
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
        charityUserExample.clear();
        charityUserExample.createCriteria().andEmailEqualTo(email);
        List<CharityUser> charityUsers = charityUserMapper.selectByExample(charityUserExample);

        if (!charityUsers.isEmpty()) {
            assert charityUsers.size() == 1;

            // get this user
            CharityUser charityUser = charityUsers.get(0);
            String userPassword = charityUser.getPassword();
            String userId = charityUser.getId();

            // md5 incoming password
            String md5Password = convertPasswordToMd5(password);

            if (md5Password.equals(userPassword)) {
                // correct && set to session
                httpSession.setAttribute(CharityConstants.HEADER_REQUEST_TOKEN, userId);
                log.info(String.format(CharityConstants.LOG_USER_LOGIN_SUCCESS, email));
                // success return
                charityUser.setPassword(null);
                return new ReturnStatus(CharityConstants.RETURN_CHARITY_USER_LOGIN_SUCCESS, charityUser);
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
    public ReturnStatus changePassword(String charityId, String password) throws SystemInternalException {
        // query charity user by id of charity user
        CharityUser charityUser = charityUserMapper.selectByPrimaryKey(charityId);

        if (!Objects.isNull(charityUser)) {
            String md5Password = convertPasswordToMd5(password);

            if (md5Password.equals(charityUser.getPassword())) {
                // password can not be the same as previous.
                log.warn(String.format(CharityConstants.LOG_CHANGE_PASSWORD_DUPLICATE, charityId));
                return new ReturnStatus(CharityConstants.RETURN_PASSWORD_DUPLICATED_ERROR,
                        CharityCodes.CHANGE_PASSWORD_DUPLICATE, StatusType.WARN);
            }

            charityUserMapper.updateByPrimaryKeySelective(new CharityUser(charityId, md5Password));
            // success return
            return new ReturnStatus(CharityConstants.RETURN_CHANGE_PASSWORD_SUCCESS);

        } else {
            // user does not exist
            log.error(String.format(CharityConstants.LOG_USER_DOES_NOT_EXIST_ID, charityId));
            return new ReturnStatus(CharityConstants.RETURN_USER_DOES_NOT_EXIST_ERROR,
                    CharityCodes.LOGIN_USER_DOES_NOT_EXIST, StatusType.FAIL);
        }
    }

    @Override
    public ReturnStatus showProfile(String id) {
        CharityUser charityUser = charityUserMapper.selectByPrimaryKey(id);
        if (Objects.isNull(charityUser)) {
            // user does not exist
            log.error(String.format(CharityConstants.LOG_USER_DOES_NOT_EXIST_ID, id));
            return new ReturnStatus(CharityConstants.RETURN_USER_DOES_NOT_EXIST_ERROR,
                    CharityCodes.LOGIN_USER_DOES_NOT_EXIST, StatusType.FAIL);
        }
        // hide password
        charityUser.setPassword(null);
        // return success
        return new ReturnStatus(CharityConstants.RETURN_USER_INFO_GET_SUCCESS, charityUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReturnStatus updateUser(CharityUser charityUser) {
        // set password && email null
        charityUser.setPassword(null);
        charityUser.setEmail(null);

        // update profile
        charityUserMapper.updateByPrimaryKeySelective(charityUser);
        return new ReturnStatus(CharityConstants.RETURN_USER_INFO_UPDATE_SUCCESS);
    }

    @Override
    public ReturnStatus getDraftMessageList(String id, int pageNum, int pageSize) {
        return getMessageList(id, pageNum, pageSize,
                Collections.singletonList(MessageType.DRAFT.getName()));
    }

    @Override
    public ReturnStatus getSendMessageList(String id, int pageNum, int pageSize) {
        return getMessageList(id, pageNum, pageSize,
                Arrays.asList(MessageType.SENT.getName(), MessageType.FAILED.getName()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReturnStatus resendMessage(String id) {
        Message message = messageMapper.selectByPrimaryKey(id);
        if (Objects.isNull(message)) {
            log.error(String.format(CharityConstants.LOG_MESSAGE_DOES_NOT_EXIST, id));
            return new ReturnStatus(CharityConstants.RETURN_MESSAGE_DOES_NOT_SAVED,
                    CharityCodes.MESSAGE_DOES_NOT_EXIST, StatusType.WARN);
        }
        return sendMessageToAllPublic(message);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReturnStatus saveOrUpdateMessage(Message message) {
        if (StringUtils.isNotBlank(message.getId())) {
            // reset modify time
            message.setModifyTime(LocalDateTime.now());
            messageMapper.updateByPrimaryKeySelective(message);
            return new ReturnStatus(CharityConstants.RETURN_UPDATE_MESSAGE_SUCCESS,
                    ImmutableMap.of(CharityConstants.DATA_MESSAGE_ID, message.getId()));
        } else {
            // set id && time && status
            message.setId(UUID.randomUUID().toString());
            message.setCreateTime(LocalDateTime.now());
            message.setModifyTime(LocalDateTime.now());
            message.setStatus(MessageType.DRAFT.getName());
            messageMapper.insertSelective(message);
            return new ReturnStatus(CharityConstants.RETURN_CREATE_MESSAGE_SUCCESS,
                    ImmutableMap.of(CharityConstants.DATA_MESSAGE_ID, message.getId()));
        }
    }

    @Override
    public ReturnStatus showDonationAmount(String id) {

        double totalDonation = 0;
        long momRatio = 0;
        long yoyRatio = 0;

        List<Donation> donations = getDonationByCharityId(id);
        if (!donations.isEmpty()) {
            // total donation
            totalDonation = donations.stream()
                    .mapToDouble(Donation::getMoney)
                    .sum();

            // Month-On-Month
            long thisMonth = 0;
            long lastMonth = 0;

            //Year-On-Year
            long thisYear = 0;
            long lastYear = 0;

            // split year and month
            LocalDate now = LocalDate.now();
            long year = now.getYear();
            Month month = now.getMonth();

            for (Donation donation : donations) {
                LocalDateTime donationTime = donation.getTime();
                if (donationTime.getYear() == year) {
                    // this year
                    thisYear += donation.getMoney();
                    if (donationTime.getMonth() == month) {
                        thisMonth += donation.getMoney();
                    }
                } else if (donationTime.getYear() == year - 1) {
                    // last year.
                    lastYear += donation.getMoney();
                    if (donationTime.getMonth() == month) {
                        lastMonth += donation.getMoney();
                    }
                }
            }
            momRatio = lastMonth == 0 ? 0 : (thisMonth - lastMonth) / lastMonth;
            yoyRatio = lastYear == 0 ? 0 : (thisYear - lastYear) / lastYear;
        }

        return new ReturnStatus(CharityConstants.RETURN_DASHBOARD_DONATION_AMOUNT_GET_SUCCESS,
                ImmutableMap.of(CharityConstants.DATA_TOTAL_DONATION, totalDonation,
                        CharityConstants.DATA_MOM_RATIO, momRatio,
                        CharityConstants.DATA_YOY_RATIO, yoyRatio));
    }

    @Override
    public ReturnStatus showDonationHistory(String id) {
        List<String> dates = Lists.newArrayList();
        List<Double> moneys = Lists.newArrayList();

        List<Donation> donations = getDonationByCharityId(id);
        if (!donations.isEmpty()) {

            donations.stream()
                    // group by time and sum result
                    .collect(
                            Collectors.groupingBy(
                                    donation -> donation.getTime().toLocalDate().toString(),
                                    Collectors.summingDouble(Donation::getMoney)
                            )
                    )
                    // split key and value
                    .forEach((date, money) -> {
                        dates.add(date);
                        moneys.add(money);
                    });
        }

        return new ReturnStatus(CharityConstants.RETURN_DASHBOARD_DONATION_HISTORY_GET_SUCCESS,
                ImmutableMap.of(CharityConstants.DATA_DATE, dates,
                        CharityConstants.DATA_DONATION, moneys));
    }

    @Override
    public ReturnStatus showDonationLocation(String id) {
        List<Donation> donations = getDonationByCharityId(id);
        List<LocationTimes> locationTimes = Lists.newArrayList();

        if (!donations.isEmpty()) {
            // query user
            publicUserExample.clear();
            publicUserExample.createCriteria()
                    .andIdIn(donations.stream()
                            .map(Donation::getPublicId)
                            .distinct()
                            .filter(Objects::nonNull)
                            .collect(Collectors.toList()));

            List<PublicUser> publicUsers = publicUserMapper.selectByExample(publicUserExample);
            publicUsers.stream()
                    .collect(
                            Collectors.groupingBy(
                                    PublicUser::getLocation,
                                    Collectors.counting()
                            )
                    ).forEach((location, times) -> {
                locationTimes.add(new LocationTimes(location, times));
            });
        }

        return new ReturnStatus(CharityConstants.RETURN_DASHBOARD_DONATION_LOCATION_GET_SUCCESS, locationTimes);
    }

    /**
     * query donation by charity id
     *
     * @param id id of charity
     * @return list of donation
     */
    private List<Donation> getDonationByCharityId(String id) {
        // query fundraising
        fundraisingExample.clear();
        fundraisingExample.createCriteria()
                .andCharityIdEqualTo(id);
        List<Fundraising> fundraisings = fundraisingMapper.selectByExample(fundraisingExample);

        // query donation
        donationExample.clear();
        donationExample.createCriteria()
                .andCharityIdEqualTo(id);
        if (!fundraisings.isEmpty()) {
            List<String> fundraisingIds = fundraisings.stream()
                    .map(Fundraising::getId)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            // bug: check null
            if (!fundraisingIds.isEmpty()) {
                donationExample.or().andFundraisingIdIn(fundraisingIds);
            }
        }
        return donationMapper.selectByExample(donationExample);
    }

    private ReturnStatus sendMessageToAllPublic(Message message) {
        List<Donation> donations = getDonationByCharityId(message.getCharityId());

        if (!donations.isEmpty()) {
            // get all public id
            List<String> publicIds = donations.stream()
                    .map(Donation::getPublicId)
                    .distinct()
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            // query all public information through id of public
            publicUserExample.clear();
            publicUserExample.createCriteria()
                    .andIdIn(publicIds);
            List<PublicUser> publicUsers = publicUserMapper.selectByExample(publicUserExample);

            // get current proxy
            CharityUserServiceImpl charityUserService = (CharityUserServiceImpl) AopContext.currentProxy();
            // send message
            publicUsers.forEach(publicUser -> charityUserService.sendMessage(publicUser, message));
            // update send status
            messageMapper.updateByPrimaryKeySelective(new Message(message.getId(), MessageType.SENT.getName()));
            return new ReturnStatus(CharityConstants.RETURN_MESSAGE_SEND_SUCCESS);
        }
        return new ReturnStatus(CharityConstants.RETURN_MESSAGE_NOTHING_SEND,
                CharityCodes.MESSAGE_NOTHING_SEND, StatusType.WARN);
    }

    @Async(value = "ThreadPoolTaskExecutor")
    public void sendMessage(PublicUser publicUser, Message message) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(publicUser.getEmail());
        msg.setSubject(message.getSubject());

        // replace template
        String content = message.getContent();
        content = content.replace(CharityConstants.MESSAGE_TEMPLATE_FIRST_NAME, publicUser.getFirstName())
                .replace(CharityConstants.MESSAGE_TEMPLATE_LAST_NAME, publicUser.getLastName());
        msg.setText(content);

        try {
            // send email
            javaMailSender.send(msg);
        } catch (MailException e) {
            log.error(String.format(CharityConstants.LOG_MESSAGE_SEND_FAILED,
                    message.getId(), publicUser.getEmail()), e);
        }
    }

    /**
     * get list of messages given status
     *
     * @param id       id of charity
     * @param pageNum  number of page
     * @param pageSize size of page
     * @param status   status list of message type
     * @return instance of ReturnStatus
     */
    private ReturnStatus getMessageList(String id, int pageNum, int pageSize,
                                        List<String> status) {
        // criteria
        messageExample.clear();
        messageExample.createCriteria()
                .andCharityIdEqualTo(id)
                .andStatusIn(status);
        messageExample.setOrderByClause(CharityConstants.SQL_ORDER_MODIFY_TIME_DESC);

        // page helper
        Page<Message> page = PageHelper.startPage(pageNum, pageSize);
        List<Message> messages = messageMapper.selectByExample(messageExample);

        // assembly data
        Map<String, Object> dataMap = Maps.newHashMap();
        dataMap.put(CharityConstants.DATA_PAGE_HELPER_TOTAL_NUMS, page.getTotal());
        dataMap.put(CharityConstants.DATA_PAGE_HELPER_TOTAL_PAGES, page.getPages());
        dataMap.put(CharityConstants.DATA_PAGE_HELPER_NOW_PAGE, page.getPageNum());
        dataMap.put(CharityConstants.DATA_PAGE_HELPER_DATA, messages);

        // return result
        return new ReturnStatus(CharityConstants.RETURN_GET_DRAFT_MESSAGE_SUCCESS, dataMap);
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
