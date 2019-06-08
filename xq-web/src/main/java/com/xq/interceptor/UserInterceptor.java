package com.xq.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import com.xq.pojo.Guest;
import com.xq.pojo.Manager;
import com.xq.utils.GuestThreadLocal;
import com.xq.utils.ManagerThreadLocal;
import com.xq.utils.ObjectMapperUtil;
import com.xq.utils.UserThreadLocal;

import redis.clients.jedis.Jedis;

@Component
public class UserInterceptor implements HandlerInterceptor{
	
	@Autowired
	private Jedis jedis;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//定义token
		String token = "";
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if("XQ_TICKET".equals(cookie.getName()) && (!StringUtils.isEmpty(cookie.getValue()))){
				//从cookie中取出token
				token = cookie.getValue();
				//根据token从redis中取出user数据
				String userJson = jedis.get(token);
				if(token.matches("Guest{1}\\S*")) {
					Guest guest = ObjectMapperUtil.getObject(userJson, Guest.class);
					//把user存进threadLocal中，以便后续操作
					GuestThreadLocal.setUser(guest);
				}else {
					Manager manager = ObjectMapperUtil.getObject(userJson, Manager.class);
					ManagerThreadLocal.setUser(manager);
				}
				return true;
			}
		}
		response.sendRedirect("/");
		return false;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		UserThreadLocal.remove();
	}
}
