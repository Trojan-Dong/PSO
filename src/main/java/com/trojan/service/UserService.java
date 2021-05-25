package com.trojan.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.trojan.entity.User;
import com.trojan.mapper.UserMapper;

@Service
public class UserService implements UserMapper {

	@Resource
	private UserMapper userMapper;

	@Override
	public User findById(int id) {
		User user = userMapper.findById(id);
		return user;

	}

	@Override
	public void addUser(User user) {
		userMapper.addUser(user);

	}

	@Override
	public User findByEmail(String email) {
		User user = userMapper.findByEmail(email);
		return user;
	}

	@Override
	public User findByLoginName(String loginName) {
		User user = userMapper.findByLoginName(loginName);
		return user;
	}
}
