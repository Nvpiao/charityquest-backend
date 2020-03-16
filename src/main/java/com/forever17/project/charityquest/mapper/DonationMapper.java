package com.forever17.project.charityquest.mapper;

import com.forever17.project.charityquest.pojos.Donation;
import com.forever17.project.charityquest.pojos.DonationExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Mapper of Donation
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 15 Feb 2020
 * @since 1.0
 */
@Repository
public interface DonationMapper {
    long countByExample(DonationExample example);

    long sumByMoney();

    int deleteByExample(DonationExample example);

    int deleteByPrimaryKey(String id);

    int insert(Donation record);

    int insertSelective(Donation record);

    List<Donation> selectByExample(DonationExample example);

    Donation selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Donation record, @Param("example") DonationExample example);

    int updateByExample(@Param("record") Donation record, @Param("example") DonationExample example);

    int updateByPrimaryKeySelective(Donation record);

    int updateByPrimaryKey(Donation record);
}