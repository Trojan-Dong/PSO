package com.trojan.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.trojan.entity.Res;
import com.trojan.mapper.ResMapper;

@Service
//service命名与userService重复
public class ResService implements ResMapper {

	@Resource
	private ResMapper resourceMapper;

	@Override
	public Res selectById() {
		Res res = resourceMapper.selectById();
		return res;
	}

	@Override
	public List<HashMap<String, Object>> selectAll() {
		List<HashMap<String, Object>> list = resourceMapper.selectAll();
		return list;
	}

	
}
