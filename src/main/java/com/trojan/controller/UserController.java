package com.trojan.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trojan.entity.User;
import com.trojan.service.UserService;

/**
 * @describe 用户相关操作
 * @author dgj
 * @date 2019年9月9日
 */
@Controller
public class UserController extends MainService{

	/**
	 * 根据id查找用户
	 * 
	 * @return
	 */
	@RequestMapping("/findUserById")
	@ResponseBody
	public User findUserById(int id) {
		logger.debug("findUserById begin");
		User user = userService.findById(id);
		logger.debug("findUserById end");
		return user;
	}

	/**
	 * 添加新用户
	 */
	@RequestMapping("/addUser")
	@ResponseBody
	public void addUser(@RequestBody User user) {
		logger.debug("addUser run");
		assert userService.findByEmail(user.getEmail()) == null : "邮箱地址已被注册";
		userService.addUser(user);
		logger.debug("addUser end");
	}

	/**
	 * 用户登录
	 */
	@RequestMapping("/login")
	@ResponseBody
	public User login(HttpServletRequest request, @RequestBody User userInfo) {
		logger.debug("login run");
		String loginName=userInfo.getLoginName();
		String password=userInfo.getPassword();
		User user = userService.findByLoginName(loginName);
		if (user == null)
			throw new RuntimeException("用户不存在");
		if (!password.equals(user.getPassword()))
			throw new RuntimeException("用户名和密码不匹配");
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		User sessionUser = (User) session.getAttribute("user");
		logger.debug(sessionUser.toString());
		logger.debug("login end");
		return user;
	}

	/**
	 * 用户登出
	 */
	@RequestMapping("/logout")
	@ResponseBody
	public void logout(HttpServletRequest request, String loginName) {
		logger.debug("logout run");
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		logger.debug("logout end");
	}

	@RequestMapping("/loginCheck")
	@ResponseBody
	public User loginCheck(HttpServletRequest request) {
		logger.debug("loginCheck begin");
		HttpSession session = request.getSession();
		User userInfo = (User) session.getAttribute("user");
		User user = null;
		if (userInfo != null) {
			user = userService.findById(userInfo.getId());
			session.setAttribute("user", user);
			logger.debug("用户" + user.getLoginName() + "已登录");
			return user;
		} else {
			logger.debug("loginCheck end");
			return null;
		}

		
	}
}
