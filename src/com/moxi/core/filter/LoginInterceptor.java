package com.moxi.core.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.moxi.model.User;

/**
 * 登陆拦截器<BR>
 * LoginInterceptor<BR>
 * 创建人:feifan<BR>
 * 时间：2017年7月1日-下午2:48:11
 * @version 1.0.0
 *
 */
public class LoginInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		User user = (User) request.getSession().getAttribute("user");
		
		String requestType = request.getHeader("X-Requested-With");
		if(user==null){
			//如果是ajax输入标志位
			if (requestType != null && requestType.equals("XMLHttpRequest")) {
				//用response的流进行输出。
				response.getWriter().print("logout");
			}else{
				//正常通过浏览器，a连接程序的window.location.href调整的地址就直接返回到首页
				response.sendRedirect(request.getContextPath());//首页居多
			}
			return false;
		}else{
			return true;
		}
	}
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
}
