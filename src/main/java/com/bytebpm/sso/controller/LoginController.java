package com.bytebpm.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bytebpm.common.Const;
import com.bytebpm.common.ServerResponse;
import com.bytebpm.mybatis.entity.TUser;
import com.bytebpm.sso.service.ILoginService;
import com.bytebpm.util.JsonUtil;
import com.bytebpm.util.RedisUtil;

@Controller
@RequestMapping("/api")
public class LoginController {

		@Autowired
		private ILoginService iUserService;
	
	    @RequestMapping(value = "/login",method = RequestMethod.POST)
	    @ResponseBody
	    public ServerResponse<TUser> login(String username, String password, HttpSession session, HttpServletResponse httpServletResponse){
	    	
	    	ServerResponse<TUser> response = iUserService.login(username,password,session);

	        if(response.isSuccess()){
            //session.setAttribute(Const.CURRENT_USER,response.getData());
		       //把JsessionID写入cookie中,作为cookie中的value
	           // CookieUtil.writeLoginToken(httpServletResponse,session.getId());
	            
	            //JsessionID作为key,用户信息(以String类型)作为value,存入redis中
	        	String token = response.getValue().getTelephone();
	        	RedisUtil.set(token, JsonUtil.obj2String(response.getValue()),(long) Const.RedisCacheExtime.REDIS_SESSION_EXTIME);
	        }
	        return response;
	    }

	    @RequestMapping(value = "/logout",method = RequestMethod.POST)
	    @ResponseBody
	    public ServerResponse<String> logout(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
	    	//session.removeAttribute(Const.CURRENT_USER);
	    	try {
	    		String token = httpServletRequest.getHeader("token");
//				String loginToken = CookieUtil.readLoginToken(httpServletRequest);
//				CookieUtil.delLoginToken(httpServletRequest, httpServletResponse);
				RedisUtil.remove(token);
				//return ServerResponse.createBySuccess();
				return ServerResponse.createBySuccessMessage("您已成功退出系统");
			} catch (Exception e) {
				e.printStackTrace();
				return ServerResponse.createByErrorMessage("服务端异常");

			}
	    }
}
