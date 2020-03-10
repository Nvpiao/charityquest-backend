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
import com.forever17.project.charityquest.pojos.entity.ReturnStatus;
import com.forever17.project.charityquest.services.CharityUserService;
import com.forever17.project.charityquest.utils.MD5Util;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
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
        // static initialization
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
                return new ReturnStatus(CharityConstants.RETURN_CharityUSER_LOGIN_SUCCESS, charityUser);
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
                Collections.singletonList(MessageType.DRAFT.name()));
    }

    @Override
    public ReturnStatus getSendMessageList(String id, int pageNum, int pageSize) {
        return getMessageList(id, pageNum, pageSize,
                Arrays.asList(MessageType.SENT.name(), MessageType.FAILED.name()));
    }

    @Override
    public ReturnStatus resendMessage(String id) {
        Message message = messageMapper.selectByPrimaryKey(id);
        if (Objects.isNull(message)) {
            log.error(String.format(CharityConstants.LOG_MESSAGE_DOES_NOT_EXIST, id));
            return new ReturnStatus(CharityConstants.RETURN_MESSAGE_DOES_NOT_EXIST,
                    CharityCodes.MESSAGE_DOES_NOT_EXIST, StatusType.FAIL);
        }
        sendMessageToAllPublic(message);
        return new ReturnStatus(CharityConstants.RETURN_MESSAGE_SEND_SUCCESS);
    }

    private void sendMessageToAllPublic(Message message) {
        // query fundraising
        fundraisingExample.clear();
        fundraisingExample.createCriteria()
                .andCharityIdEqualTo(message.getCharityId());
        List<Fundraising> fundraisings = fundraisingMapper.selectByExample(fundraisingExample);

        // query donation
        donationExample.clear();
        donationExample.createCriteria()
                .andCharityIdEqualTo(message.getCharityId());
        donationExample.or()
                .andFundraisingIdIn(fundraisings.stream()
                        .map(Fundraising::getId)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList()));
        List<Donation> donations = donationMapper.selectByExample(donationExample);

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
        }
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
        dataMap.put(CharityConstants.PAGE_HELPER_TOTAL_NUMS, page.getTotal());
        dataMap.put(CharityConstants.PAGE_HELPER_TOTAL_PAGES, page.getPages());
        dataMap.put(CharityConstants.PAGE_HELPER_NOW_PAGE, page.getPageNum());
        dataMap.put(CharityConstants.PAGE_HELPER_DATA, messages);

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
