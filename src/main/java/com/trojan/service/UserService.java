package com.trojan.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.trojan.entity.User;
import com.trojan.dao.UserDao;

@Service
public class UserService implements UserDao {

	@Resource
	private UserDao userDao;

	@Override
	public User findById(int id) {
		User user = userDao.findById(id);
		return user;

	}

	@Override
	public void addUser(User user) {
		userDao.addUser(user);

	}

	@Override
	public User findByEmail(String email) {
		User user = userDao.findByEmail(email);
		return user;
	}

	@Override
	public User findByLoginName(String loginName) {
		User user = userDao.findByLoginName(loginName);
		return user;
	}
}
