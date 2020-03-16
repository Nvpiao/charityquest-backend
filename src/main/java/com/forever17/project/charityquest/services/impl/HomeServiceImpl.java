package com.forever17.project.charityquest.services.impl;

import com.forever17.project.charityquest.constants.CharityConstants;
import com.forever17.project.charityquest.mapper.DonationMapper;
import com.forever17.project.charityquest.mapper.PublicUserMapper;
import com.forever17.project.charityquest.pojos.DonationExample;
import com.forever17.project.charityquest.pojos.PublicUserExample;
import com.forever17.project.charityquest.pojos.entity.ReturnStatus;
import com.forever17.project.charityquest.services.HomeService;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Implementation of HomeService
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 16 Mar 2020
 * @since 1.0
 */
@Slf4j
@Service
public class HomeServiceImpl implements HomeService {

    /**
     * mapper of donation
     */
    private final DonationMapper donationMapper;
    /**
     * mapper of public user
     */
    private final PublicUserMapper publicUserMapper;
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

    public HomeServiceImpl(DonationMapper donationMapper, PublicUserMapper publicUserMapper) {
        this.donationMapper = donationMapper;
        this.publicUserMapper = publicUserMapper;
    }

    @Override
    public ReturnStatus details() {
        // query number of donor
        publicUserExample.clear();
        publicUserExample.createCriteria();
        long userNum = publicUserMapper.countByExample(publicUserExample);

        // query number of donation
        long moneySum = donationMapper.sumByMoney();

        return new ReturnStatus(CharityConstants.RETURN_HOME_DETAIL_GET_SUCCESS,
                ImmutableMap.of(CharityConstants.DATA_TOTAL_DONATION, userNum,
                        CharityConstants.DATA_TOTAL_DONATOR, moneySum));
    }
}
