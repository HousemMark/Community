package com.xq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xq.service.UserService;
import com.xq.vo.WebResult;

@Controller
@RequestMapping("/guest/")
public class GuestController {
	
	@Reference(check=false)
	UserService userService;
	
	//查询住户信息列表
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public WebResult doFindPageObjects(String username, Integer pageCurrent) {
		try {
			return WebResult.ok(userService.findGuests(username,pageCurrent));
		} catch (Exception e) {
			e.printStackTrace();
			return WebResult.fail();
		}
	}
	
}
