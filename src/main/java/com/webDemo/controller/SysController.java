package com.webDemo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webDemo.entity.Menu;
import com.webDemo.entity.User;
import com.webDemo.service.MenuService;
import com.webDemo.service.UserService;

@Controller
@RequestMapping("/sys")
public class SysController {
	

	private final String head_path = "sys/";
	
	@Autowired
	private UserService userService;
	@Autowired
	private MenuService menuService;
	
	
	
	@RequestMapping("toLogin.html")
	public String toLogin(){
		return head_path+"login";
	}
	
	@RequestMapping("menu.html")//method=RequestMethod.POST
	@ResponseBody
	public String menu() throws Exception{
		List<Menu> jsonList = new ArrayList<>();
		List<Menu> menuList = menuService.queryMenuList();
		
		if(menuList!=null && menuList.size()>0){
			//maxLevel
			int maxLevel = menuList.get(0).getLevel();
			for(int i=maxLevel;i>0;i--){
				List<Menu> cList = findMenuByLevel(i,menuList);//menuService.queryMenusListByLevel(i);
			 	List<Menu> fList = findMenuByLevel(i-1,menuList);//menuService.queryMenusListByLevel(i-1);
			 	for(int m=0;m<fList.size();m++){
			 		Menu fMenu = fList.get(m);
			 		for(int n=0;n<cList.size();n++){
			 			Menu cMenu = cList.get(n);
			 			if(cMenu.getParentid().equals(fMenu.getId())){
			 				List<Menu> children = fMenu.getChildren();
			 				if(children==null){
			 					children = new ArrayList<Menu>();
			 				}
			 				children.add(cMenu);
			 				fMenu.setChildren(children);
			 			}
			 		}
			 		fList.set(m,fMenu);
			 	}
			 	
			 	if(i==1)//最高level的Menu
			 		jsonList = fList;
			}
		}
		
		ObjectMapper oMapper = new ObjectMapper();
		String json = oMapper.writeValueAsString(jsonList);
		
		return json;
	}
	
	
	
	public List<Menu> findMenuByLevel(int level,List<Menu> menuList){
		List<Menu> findList = new ArrayList<Menu>();
		for(Menu menu:menuList){
			if(menu.getLevel()==level)
				findList.add(menu);
		}
		return findList;
	}
	
	
	
	
	
	
	@RequestMapping("login.html")
	public String login(HttpServletRequest request,Model model,User user){
		String returnPage = head_path+"login";
		//验证码
		String kaptchaCode = (String)request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		String checkcode = request.getParameter("checkcode");
		if(checkcode!=null && checkcode.trim().length()>0){
			if(!checkcode.equals(kaptchaCode)){
				model.addAttribute("CODE_ERROR", "验证码不一致");
				return returnPage;
			}
		}else{
			model.addAttribute("CODE_ERROR", "验证码不能为空");
			return returnPage;
		}
		
		//验证用户数据
		String username = user.getUsername();
		String password = user.getPassword();
		if(username==null ||username.trim().length()==0|| password==null||password.trim().length()==0){
			model.addAttribute("LOGIN_ERROR", "用户名或密码不能为空");
			return returnPage;
		}else{
			User record = userService.queryUserByUser(user);
			if(record==null){
				model.addAttribute("LOGIN_ERROR", "用户不存在");
			}else{
				model.addAttribute("currUser", record);
				request.getSession().setAttribute("currUser", record);
				returnPage = head_path+"main";
			}
		}
		
		return returnPage;
	}
	
	
	
	
	
	
	@RequestMapping("test111.html")
	public String test111(){
		return head_path+"test111";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
