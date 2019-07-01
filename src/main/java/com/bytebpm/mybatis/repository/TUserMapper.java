package com.bytebpm.mybatis.repository;

import com.bytebpm.mybatis.entity.TUser;
import com.bytebpm.mybatis.entity.TUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TUserMapper {
    int countByExample(TUserExample example);

    int deleteByExample(TUserExample example);

    int deleteByPrimaryKey(String id);

    long insert(TUser record);

    long insertSelective(TUser record);

    List<TUser> selectByExample(TUserExample example);

    TUser selectByPrimaryKey(String id);
    
    //新加登录方法.通过username与password,返回TUser对象
    TUser selectLogin(@Param("username") String username, @Param("password") String password);
    //新加方法,通过username获取数据库中的TUser
    TUser selectByName(String username);
    //新加方法,查询username是否存在
    int existUsername(String username);
    
    int updateByExampleSelective(@Param("record") TUser record, @Param("example") TUserExample example);

    int updateByExample(@Param("record") TUser record, @Param("example") TUserExample example);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);
}