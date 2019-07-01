package com.bytebpm.mybatis.repository;

import com.bytebpm.mybatis.entity.TRoleUser;
import com.bytebpm.mybatis.entity.TRoleUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TRoleUserMapper {
    int countByExample(TRoleUserExample example);

    int deleteByExample(TRoleUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TRoleUser record);

    int insertSelective(TRoleUser record);

    List<TRoleUser> selectByExample(TRoleUserExample example);

    TRoleUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TRoleUser record, @Param("example") TRoleUserExample example);

    int updateByExample(@Param("record") TRoleUser record, @Param("example") TRoleUserExample example);

    int updateByPrimaryKeySelective(TRoleUser record);

    int updateByPrimaryKey(TRoleUser record);
}