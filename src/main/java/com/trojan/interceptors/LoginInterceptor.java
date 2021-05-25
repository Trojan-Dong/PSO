package com.trojan.interceptors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.trojan.entity.User;
import com.trojan.service.UserService;

@Component
public class LoginInterceptor implements HandlerInterceptor {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private UserService userService;

	/*
	 * 访问前拦截
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.debug("preHandle begin");
		HttpSession session = request.getSession();// 获取session
		User userInfo = (User) session.getAttribute("user");
		User user = null;
		if (userInfo != null) {
			user = userService.findById(userInfo.getId());
			session.setAttribute("user", user);
			logger.debug("用户" + user.getLoginName() + "已登录");
			logger.debug("preHandle end");
			return true;
		} else {
			logger.debug("preHandle end");
			return false;
		}
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {

		logger.debug("postHandle begin");
		logger.debug("postHandle begin");
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {
		logger.debug("afterCompletion begin");
		logger.debug("afterCompletion begin");
	}

}
