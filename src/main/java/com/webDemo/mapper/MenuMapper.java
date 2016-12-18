package com.webDemo.mapper;

import java.util.List;

import com.webDemo.entity.Menu;

public interface MenuMapper {
	
	List<Menu> selectAllMenus();
	
	List<Menu> selectMenuByLevel(int level);
	
	List<Menu> selectMenuListByUserName(String username);
	
	
	
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);




}