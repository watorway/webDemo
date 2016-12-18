package com.webDemo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.webDemo.entity.User;
import com.webDemo.mapper.UserMapper;
import com.webDemo.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;
	
	
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED,rollbackForClassName="RuntimeException")
	public User queryUserById(String id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public User queryUserByUser(User user) {
		return userMapper.selectByUser(user);
	}
	
	
	
	
	
	
	
	
	
}
