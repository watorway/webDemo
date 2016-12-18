package com.webDemo.service;

import java.util.List;

import com.webDemo.entity.Menu;

public interface MenuService {
	
	List<Menu> queryMenuList();

	List<Menu> queryMenusListByLevel(int level);
	
}
