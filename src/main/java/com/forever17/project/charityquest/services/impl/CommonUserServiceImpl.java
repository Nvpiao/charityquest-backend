package com.forever17.project.charityquest.services.impl;

import com.forever17.project.charityquest.constants.CharityConstants;
import com.forever17.project.charityquest.pojos.entity.ResetPasswordCode;
import com.forever17.project.charityquest.pojos.entity.ReturnStatus;
import com.forever17.project.charityquest.services.CommonUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Implementation of CommonUserServiceImpl
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 7 Mar 2020
 * @since 1.0
 */
@Slf4j
@Service
public class CommonUserServiceImpl implements CommonUserService {

    /**
     * code for resetting password
     */
    private final ResetPasswordCode resetPasswordCode;

    /**
     * java mail sender
     */
    private final JavaMailSender javaMailSender;

    @Autowired
    public CommonUserServiceImpl(ResetPasswordCode resetPasswordCode, JavaMailSender javaMailSender) {
        this.resetPasswordCode = resetPasswordCode;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public ReturnStatus sendResetPassword(String email) {
        // simple mail message
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setSubject(CharityConstants.MAIL_SUBJECT_RESET_PASSWORD);

        // generate code
        String code = resetPasswordCode.generateCode(email);
        String text = CharityConstants.MAIL_TEXT_RESET_PASSWORD + '\n' +
                resetPasswordCode.getBaseUrl() + code;

        // set text
        msg.setText(text);

        // send email
        javaMailSender.send(msg);

        // return success
        return new ReturnStatus(CharityConstants.RETURN_SEND_RESET_EMAIL_SUCCESS);
    }
}
