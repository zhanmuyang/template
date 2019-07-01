package com.bytebpm.sso.controller.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.bytebpm.mybatis.entity.TUser;
import com.bytebpm.sso.service.ILoginService;
/**
 * 自定义的realm域对象
 * @author Rita
 *
 */
public class AuthRealm extends AuthorizingRealm{
	
	@Autowired
	private ILoginService LoginService;
	
	//授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//通过token去数据库查
		UsernamePasswordToken upToken = (UsernamePasswordToken)token;
		String username = upToken.getUsername();
		TUser tUser = LoginService.selectByName(username);
		//如果通过username,获得的tUser不为null,就说明有这个tUser
		if(tUser!=null){
			//把"从数据库查出来生成的tUser对象"与"password"放入SimpleAuthenticationInfo类中
			//最重要的是password,后面需要用.
			SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(tUser, tUser.getPassword(), "abc");
			return info;
		}
		//如果通过username,查不到tUSer(TUser等于null)
		return null;
	}

}
