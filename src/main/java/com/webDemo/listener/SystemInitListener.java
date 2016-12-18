package com.webDemo.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.webDemo.cache.CacheManage;


public class SystemInitListener implements ServletContextListener{

	/**
	 * 正常关闭服务器
	 */
	@Override
	public void contextDestroyed(ServletContextEvent contextEvent) {
		System.out.println("WebContext End!");
		System.out.println("WebContext End!");
	}

	@Override
	public void contextInitialized(ServletContextEvent contextEvent) {
		systemStartup(contextEvent.getServletContext());
	}

	/**
	 * 应用平台启动
	 */
	@SuppressWarnings("all")
	private void systemStartup(ServletContext servletContext) {
		System.out.println("********************************************");
		WebApplicationContext   ctx
			= WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext); 
		
		CacheManage<String,Object> cacheManage = (CacheManage<String,Object>)ctx.getBean("cacheManage");
		cacheManage.get("dictList");
		
		
		System.out.println("系统开始加载全局参数表.......");
		System.out.println("********************************************");
	}
	
	
	
}
