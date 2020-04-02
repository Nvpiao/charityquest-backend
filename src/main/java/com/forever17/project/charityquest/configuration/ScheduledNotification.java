package com.forever17.project.charityquest.configuration;

import com.forever17.project.charityquest.constants.CharityConstants;
import com.forever17.project.charityquest.enums.DonationTimes;
import com.forever17.project.charityquest.enums.DonationType;
import com.forever17.project.charityquest.mapper.CharityUserMapper;
import com.forever17.project.charityquest.mapper.DonationMapper;
import com.forever17.project.charityquest.mapper.PublicUserMapper;
import com.forever17.project.charityquest.pojos.CharityUser;
import com.forever17.project.charityquest.pojos.Donation;
import com.forever17.project.charityquest.pojos.DonationExample;
import com.forever17.project.charityquest.pojos.PublicUser;
import com.forever17.project.charityquest.pojos.PublicUserExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ScheduledNotification {

    private final JavaMailSender javaMailSender;
    /**
     * mapper of donation
     */
    private final DonationMapper donationMapper;

    /**
     * mapper of public user
     */
    private final PublicUserMapper publicUserMapper;

    /**
     * mapper of charity user
     */
    private final CharityUserMapper charityUserMapper;

    /**
     * example of donation
     */
    private DonationExample donationExample;

    /**
     * example of public user
     */
    private PublicUserExample publicUserExample;

    {
        // static initialization
        donationExample = new DonationExample();
        publicUserExample = new PublicUserExample();
    }

    @Autowired
    public ScheduledNotification(JavaMailSender javaMailSender, DonationMapper donationMapper,
                                 PublicUserMapper publicUserMapper, CharityUserMapper charityUserMapper) {
        this.javaMailSender = javaMailSender;
        this.donationMapper = donationMapper;
        this.publicUserMapper = publicUserMapper;
        this.charityUserMapper = charityUserMapper;
    }

    @Scheduled(cron = "${cron.scheduled}")
    @Transactional(rollbackFor = Exception.class)
    public void notifyToDonate() {
        // enabled regular donation
        donationExample.clear();
        donationExample.createCriteria()
                .andTypeEqualTo(DonationType.DONATION.getName())
                .andDonateTypeNotEqualTo(DonationTimes.ONCE.getName())
                .andStatusEqualTo(CharityConstants.DONATION_STATUS_ENABLED);

        List<Donation> donations = donationMapper.selectByExample(donationExample);
        if (!donations.isEmpty()) {
            List<String> publicIds = donations.stream()
                    .map(Donation::getPublicId)
                    .collect(Collectors.toList());

            // query public user
            publicUserExample.clear();
            publicUserExample.createCriteria().andIdIn(publicIds);
            List<PublicUser> publicUsers = publicUserMapper.selectByExample(publicUserExample);

            donations.stream()
                    .filter(donation -> {
                        LocalDate lastDonationTime = LocalDate.from(donation.getTime());
                        DonationTimes donationTimes = DonationTimes.valueOf(donation.getDonateType().toUpperCase());

                        LocalDate now = LocalDate.now();
                        long days = Duration.between(lastDonationTime, now).toDays();
                        switch (donationTimes) {
                            case WEEKLY:
                                return days >= 6;
                            case MONTHLY:
                                return days >= 30;
                            case QUARTERLY:
                                return days >= 91;
                            case YEARLY:
                                return days >= 364;
                            default:
                                return false;
                        }
                    })
                    .forEach(donation -> {
                        PublicUser specificUser = publicUsers.stream()
                                .filter(publicUser -> publicUser.getId().equals(donation.getId()))
                                .findFirst()
                                .get();
                        CharityUser charityUser = charityUserMapper.selectByPrimaryKey(donation.getCharityId());
                        notifyToDonateByEmail(specificUser, charityUser.getName());
                        // update time
                        donation.setTime(LocalDateTime.now());
                        donationMapper.updateByPrimaryKeySelective(donation);
                    });
        }
    }

    @Async(value = "ThreadPoolTaskExecutor")
    public void notifyToDonateByEmail(PublicUser publicUser, String name) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(publicUser.getEmail());
        msg.setSubject(CharityConstants.MAIL_SUBJECT_DONATION_REMINDER);

        // replace template
        String content = CharityConstants.MAIL_TEXT_DONATION_REMINDER
                .replace(CharityConstants.MESSAGE_TEMPLATE_CHARITY_NAME, name)
                .replace(CharityConstants.MESSAGE_TEMPLATE_FIRST_NAME, publicUser.getFirstName())
                .replace(CharityConstants.MESSAGE_TEMPLATE_LAST_NAME, publicUser.getLastName());
        msg.setText(content);

        try {
            // send email
            javaMailSender.send(msg);
        } catch (MailException e) {
            log.error(String.format(CharityConstants.LOG_DONATION_REMINDER_SEND_FAILED, publicUser.getId(), name), e);
        }
    }
}
