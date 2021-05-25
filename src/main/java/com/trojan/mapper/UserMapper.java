package com.trojan.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.trojan.entity.User;

@Mapper
public interface UserMapper {

	public User findById(int id);

	public void addUser(User user);

	public User findByEmail(String email);

	public User findByLoginName(String loginName);
}
