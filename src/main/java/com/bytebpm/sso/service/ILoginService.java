package com.bytebpm.sso.service;

import javax.servlet.http.HttpSession;

import com.bytebpm.common.ServerResponse;
import com.bytebpm.mybatis.entity.TUser;

public interface ILoginService {
	ServerResponse<TUser> login(String username, String password, HttpSession session);

	TUser selectByName(String username);

	//此方法可以通过Tuser对象,把用户信息添加到数据库中
	long insertUser(TUser user);

}
