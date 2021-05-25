package com.trojan.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.trojan.entity.Category;
@Mapper
public interface CategoryMapper {
	public Category selectById();

	public List<HashMap<String, Object>> selectAll();

	public void addCategory(Category category);
	public Category selectCateByName(String cateName);
}
