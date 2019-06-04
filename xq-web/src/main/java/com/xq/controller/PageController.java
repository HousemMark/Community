package com.xq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	
	@RequestMapping("/doPageUI")
	public String doPageUI() {
		return "common/page";
	}
	
	@RequestMapping("/")
	public String doIndex() {
		return "index";
	}
	
	@RequestMapping("/guestList")
	public String doGuestList() {
		return "guestStarter";
	}
	
	@RequestMapping("/managerList")
	public String doManagerList() {
		return "managerStarter";
	}
	
	@RequestMapping("/manager/index/{page}")
	public String doManagerPage(@PathVariable String page) {
		return "manager/"+page;
	}
	
	@RequestMapping("/guest/index/{page}")
	public String doGuestPage(@PathVariable String page) {
		return "guest/"+page;
	}
}
