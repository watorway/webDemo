package com.webDemo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webDemo.entity.Menu;
import com.webDemo.mapper.MenuMapper;
import com.webDemo.service.MenuService;

@Service
@Transactional
public class MenuServiceImpl implements MenuService{
	
	@Autowired
	private MenuMapper menuMapper;

	@Override
	public List<Menu> queryMenuList() {
		return menuMapper.selectAllMenus();
	}

	@Override
	public List<Menu> queryMenusListByLevel(int level) {
		return menuMapper.selectMenuByLevel(level);
	}
	
	
}
