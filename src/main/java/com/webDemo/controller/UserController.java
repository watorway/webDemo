package com.webDemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/")
public class UserController extends BaseController{
	
	
	@RequestMapping("userListPage")
//	@RequestMapping("userListPage.html") 加上.html也一样
	public String toUserListPage(){
		return "user/userList";
	}
	
	
	
	
}
