package com.bytebpm.sso.controller.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

import com.bytebpm.util.Encrypt;

public class UserCredentialsMatcher extends SimpleCredentialsMatcher{
	/**
	 * 重写密码比较的方法
	 * AuthenticationToken token	登录页面传过来的用户名和密码（没有加密）
	 * AuthenticationInfo info		从数据库中查询到密码（加密的）
	 */															 
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		// 前端:获取到前端输入的用户名
		String username = upToken.getUsername();
		// 前端:获取到前端输入的密码
		String pwd = new String(upToken.getPassword());
		// 前端:对前端输入的pwd加密
		String formPwd = Encrypt.md5(pwd, username);
		//数据库:从数据库查出来的密码(加密的)
		String dbPwd = (String) info.getCredentials();
		// 对比----前端与数据库
		return super.equals(formPwd, dbPwd);
	}

}
