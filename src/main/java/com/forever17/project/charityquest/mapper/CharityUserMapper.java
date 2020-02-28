package com.forever17.project.charityquest.mapper;

import com.forever17.project.charityquest.pojos.CharityUser;
import com.forever17.project.charityquest.pojos.CharityUserExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Mapper of Charity User
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 15 Feb 2020
 * @since 1.0
 */
@Repository
public interface CharityUserMapper {
    long countByExample(CharityUserExample example);

    int deleteByExample(CharityUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(CharityUser record);

    int insertSelective(CharityUser record);

    List<CharityUser> selectByExample(CharityUserExample example);

    CharityUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CharityUser record, @Param("example") CharityUserExample example);

    int updateByExample(@Param("record") CharityUser record, @Param("example") CharityUserExample example);

    int updateByPrimaryKeySelective(CharityUser record);

    int updateByPrimaryKey(CharityUser record);
}