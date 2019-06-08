package com.xq.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xq.pojo.Guest;
import com.xq.service.GuestPwdService;
import com.xq.utils.GuestThreadLocal;
import com.xq.vo.WebResult;

@RestController
public class PwdController {
	
	@Reference(check=false)
	GuestPwdService guestPwdService;
	
	@RequestMapping("/guest/doUpdatePassword")
	public WebResult doUpdatePassword(String pwd, String newPwd, String cfgPwd) {
		Guest guest = GuestThreadLocal.getUser();
		System.out.println(guest.getSalt());
		return WebResult.ok();
	}
}
