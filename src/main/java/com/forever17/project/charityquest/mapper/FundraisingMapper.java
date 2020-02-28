package com.forever17.project.charityquest.mapper;

import com.forever17.project.charityquest.pojos.Fundraising;
import com.forever17.project.charityquest.pojos.FundraisingExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FundraisingMapper {
    long countByExample(FundraisingExample example);

    int deleteByExample(FundraisingExample example);

    int deleteByPrimaryKey(String id);

    int insert(Fundraising record);

    int insertSelective(Fundraising record);

    List<Fundraising> selectByExample(FundraisingExample example);

    Fundraising selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Fundraising record, @Param("example") FundraisingExample example);

    int updateByExample(@Param("record") Fundraising record, @Param("example") FundraisingExample example);

    int updateByPrimaryKeySelective(Fundraising record);

    int updateByPrimaryKey(Fundraising record);
}