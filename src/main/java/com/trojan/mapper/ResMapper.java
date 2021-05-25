package com.trojan.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.trojan.entity.Res;
@Mapper
public interface ResMapper {
	public Res selectById();

	public List<HashMap<String, Object>> selectAll();
}
