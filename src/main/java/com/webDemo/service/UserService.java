package com.webDemo.service;

import com.webDemo.entity.User;

public interface UserService {

	User queryUserById(String id);

	User queryUserByUser(User user);

}
