package com.bytebpm.sso.service.impl;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bytebpm.common.ServerResponse;
import com.bytebpm.mybatis.entity.TUser;
import com.bytebpm.mybatis.repository.TUserMapper;
import com.bytebpm.sso.service.ILoginService;
import com.bytebpm.util.Encrypt;

@Service("iUserService")
public class LoginServiceImpl implements ILoginService{
	
	@Autowired
	private TUserMapper tUserMapper;

	@Override
	public ServerResponse<TUser> login(String username, String password,HttpSession session) {
		
		//查询是否存在这个username
        int resultCount = tUserMapper.existUsername(username);
        if(resultCount == 0 ){
            return ServerResponse.createByErrorMessage("用户名不存在");
        }

    	try {
			//访问shiro安全管理器做认证
			//获取到subject对象
			Subject subject = SecurityUtils.getSubject();
			//通过name和password,得到shiro所需的token
			UsernamePasswordToken token = new UsernamePasswordToken(username,password);
			//把token传给subject,让subject自己去认证
			subject.login(token);
			// 如果没有抛异常,说明验证通过
			//通过getPrincipal()方法只能获得于user有关的类,因为你只传了username与password
			TUser tUser = (TUser)subject.getPrincipal();

			 //邮箱系统需要用到username与password,所以password不能置空
	        //user.setPassword(org.apache.commons.lang3.StringUtils.EMPTY);
			ObjectId id = new ObjectId();
			String stringId = id.toString();
			tUser.setTelephone(stringId);
	        return ServerResponse.createBySuccess("登录成功",tUser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ServerResponse.createByErrorMessage("用户名或密码错误");
		}

	}

	@Override
	public TUser selectByName(String username) {
		TUser tuser = tUserMapper.selectByName(username);
		return tuser;
	}
	
	//传过来明文密码就ok
	//通过对象,把用户信息添加到MySQL数据库中
	@Override
	public long insertUser(TUser user) {
		// TODO Auto-generated method stub
		//明文密码
		String password = user.getPassword();
		String username = user.getUsername();
		//对明文密码进行加密
		String formPwd = Encrypt.md5(password, username);
		user.setPassword(formPwd);
		long isSuccess = tUserMapper.insertSelective(user);
		
		return isSuccess;
	}


}
