package com.webDemo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.webDemo.entity.User;

@Component
public class BaseController {
	
	
	public User getCurrentUser(HttpServletRequest request){
		User currUser = (User)request.getSession().getAttribute("currUser");
		return currUser;
	}
	
	
	
	
}
