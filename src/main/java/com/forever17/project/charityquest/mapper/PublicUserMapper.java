package com.forever17.project.charityquest.mapper;

import com.forever17.project.charityquest.pojos.PublicUser;
import com.forever17.project.charityquest.pojos.PublicUserExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Mapper of Public User
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 15 Feb 2020
 * @since 1.0
 */
@Repository
public interface PublicUserMapper {
    long countByExample(PublicUserExample example);

    int deleteByExample(PublicUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(PublicUser record);

    int insertSelective(PublicUser record);

    List<PublicUser> selectByExample(PublicUserExample example);

    PublicUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PublicUser record, @Param("example") PublicUserExample example);

    int updateByExample(@Param("record") PublicUser record, @Param("example") PublicUserExample example);

    int updateByPrimaryKeySelective(PublicUser record);

    int updateByPrimaryKey(PublicUser record);
}