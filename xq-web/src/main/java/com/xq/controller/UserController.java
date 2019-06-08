package com.xq.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xq.vo.WebResult;

@RestController
@RequestMapping("/user/")
public class UserController {
	
	@RequestMapping("doFindGuestPageObjects")
	public WebResult doFindGuestPageObjects(Integer pageCurrent) {
		try {
			return WebResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return WebResult.fail();
		}
	}
	
	@RequestMapping("doFindManagerPageObjects")
	public WebResult doFindManagerPageObjects(Integer pageCurrent) {
		try {
			return WebResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return WebResult.fail();
		}
	}
}
