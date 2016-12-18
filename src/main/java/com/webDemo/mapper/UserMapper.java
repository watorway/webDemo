package com.webDemo.mapper;

import org.springframework.stereotype.Component;

import com.webDemo.entity.User;

@Component
public interface UserMapper {
	
	User selectByUser(User user);
	
	User selectByUsername(String username);
	
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

}