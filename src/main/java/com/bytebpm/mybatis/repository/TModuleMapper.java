package com.bytebpm.mybatis.repository;

import com.bytebpm.mybatis.entity.TModule;
import com.bytebpm.mybatis.entity.TModuleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TModuleMapper {
    int countByExample(TModuleExample example);

    int deleteByExample(TModuleExample example);

    int deleteByPrimaryKey(String id);

    int insert(TModule record);

    int insertSelective(TModule record);

    List<TModule> selectByExample(TModuleExample example);

    TModule selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TModule record, @Param("example") TModuleExample example);

    int updateByExample(@Param("record") TModule record, @Param("example") TModuleExample example);

    int updateByPrimaryKeySelective(TModule record);

    int updateByPrimaryKey(TModule record);
}