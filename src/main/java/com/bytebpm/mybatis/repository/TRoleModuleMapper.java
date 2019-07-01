package com.bytebpm.mybatis.repository;

import com.bytebpm.mybatis.entity.TRoleModule;
import com.bytebpm.mybatis.entity.TRoleModuleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TRoleModuleMapper {
    int countByExample(TRoleModuleExample example);

    int deleteByExample(TRoleModuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TRoleModule record);

    int insertSelective(TRoleModule record);

    List<TRoleModule> selectByExample(TRoleModuleExample example);

    TRoleModule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TRoleModule record, @Param("example") TRoleModuleExample example);

    int updateByExample(@Param("record") TRoleModule record, @Param("example") TRoleModuleExample example);

    int updateByPrimaryKeySelective(TRoleModule record);

    int updateByPrimaryKey(TRoleModule record);
}