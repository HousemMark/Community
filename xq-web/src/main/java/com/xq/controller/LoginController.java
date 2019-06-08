package com.xq.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xq.pojo.User;
import com.xq.service.LoginService;
import com.xq.vo.WebResult;

@RestController
public class LoginController {
	@Reference(check=false)
	private LoginService loginService;
	
	@RequestMapping("doLogin")
	public WebResult login(boolean isRememberMe,User user,HttpServletResponse response) {
		try {
			UsernamePasswordToken upToken = new UsernamePasswordToken();
			if(isRememberMe)
				upToken.setRememberMe(isRememberMe);
			String role = loginService.confirmUser(user);
			//单点登陆操作
			String token = loginService.getToken();
			Cookie cookie = new Cookie("XQ_TICKET", token);
			//设置cookie属性
			cookie.setDomain("yuexiu.com");
			cookie.setMaxAge(-1);
			cookie.setPath("/");
			response.addCookie(cookie);
			return WebResult.ok(role);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return WebResult.fail();
	}
}
