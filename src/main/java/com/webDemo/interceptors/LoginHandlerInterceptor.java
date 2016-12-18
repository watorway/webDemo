package com.webDemo.interceptors;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.webDemo.entity.User;

public class LoginHandlerInterceptor extends HandlerInterceptorAdapter  {
	private List<String> excludeUrls;

	public List<String> getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}
	
	
	/**
	 * 在controller前拦截
	 */
    public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
    	request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
    	String requestPath = this.getRequestPath(request);// 用户访问的资源地址
		HttpSession session = this.getSession();
		User currUser = (User)session.getAttribute("currUser");
		//如果是开放的资源
		for (String url : excludeUrls) {
			if (requestPath.contains(url)) {
				return true;
			}
		}
		
		if (currUser != null ) {
			return true;
		} else {//没有登录，首页登录
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return false;
		}
		
    } 
    
    /**
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作    
     */
  	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {

  	}
  	
    /**
	 * 在controller后拦截
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) throws Exception {
	
	}
	
	
	
	
	
	public String getRequestPath(HttpServletRequest request) {
		String requestPath = request.getRequestURI() + "?" + request.getQueryString();
		if (requestPath.indexOf("&") > -1) {// 去掉其他参数
			requestPath = requestPath.substring(0, requestPath.indexOf("&"));
		}
		requestPath = requestPath.substring(request.getContextPath().length() + 1);// 去掉项目路径
		return requestPath;
	}

	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request;

	}
	/**
	 * SpringMvc下获取session
	 * 
	 * @return
	 */
	public HttpSession getSession() {
		HttpSession session = getRequest().getSession();
		//session.setMaxInactiveInterval(1800);
		return session;

	}
}