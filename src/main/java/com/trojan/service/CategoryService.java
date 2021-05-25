package com.trojan.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.trojan.entity.Category;
import com.trojan.entity.Directory;
import com.trojan.entity.Res;
import com.trojan.mapper.CategoryMapper;
import com.trojan.mapper.DirectoryMapper;
import com.trojan.mapper.ResMapper;

@Service
//service命名与userService重复
public class CategoryService implements CategoryMapper {

	@Resource
	private CategoryMapper categoryMapper;

	@Override
	public Category selectById() {
		Category category = categoryMapper.selectById();
		return category;
	}

	@Override
	public List<HashMap<String, Object>> selectAll() {
		List<HashMap<String, Object>> list = categoryMapper.selectAll();
		return list;
	}

	@Override
	public void addCategory(Category category) {
		categoryMapper.addCategory(category);
	}

	@Override
	public Category selectCateByName(String novelName) {
		Category category = categoryMapper.selectCateByName(novelName);
		return category;
	}

}
