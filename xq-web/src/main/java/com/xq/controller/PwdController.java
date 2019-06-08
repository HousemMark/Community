package com.xq.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xq.pojo.Guest;
import com.xq.pojo.Manager;
import com.xq.service.PwdService;
import com.xq.utils.GuestThreadLocal;
import com.xq.utils.ManagerThreadLocal;
import com.xq.vo.WebResult;

@RestController
public class PwdController {
	
	@Reference(check=false)
	PwdService guestPwdService;
	
	@RequestMapping("/pwd/doUpdateGuestPassword")
	public WebResult doUpdateGuestPassword(String pwd, String newPwd, String cfgPwd) {
		try {
			Guest guest = GuestThreadLocal.getUser();
			System.out.println(guest.getUsername());
			Integer res = guestPwdService.updatedGuestPwd(guest, pwd, newPwd, cfgPwd);
			System.out.println("Updated Done!");
			return WebResult.ok(res);
		} catch (Exception e) {
			e.printStackTrace();
			return WebResult.fail(e);
		}
	}
	
	@RequestMapping("/pwd/doUpdateManagerPassword")
	public WebResult doUpdateManagerPassword(String pwd, String newPwd, String cfgPwd) {
		try {
			Manager manager = ManagerThreadLocal.getUser();
			System.out.println(manager.getUsername());
			Integer res = guestPwdService.updatedManagerPwd(manager, pwd, newPwd, cfgPwd);
			System.out.println("Updated Done!");
			return WebResult.ok(res);
		} catch (Exception e) {
			e.printStackTrace();
			return WebResult.fail(e);
		}
	}
}
