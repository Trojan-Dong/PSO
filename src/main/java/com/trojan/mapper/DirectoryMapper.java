package com.trojan.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.trojan.entity.Category;
import com.trojan.entity.Directory;
@Mapper
public interface DirectoryMapper {
	public Directory selectById(int id);

	public List<HashMap<String, Object>> selectAll();

	public void addNovel(Directory directory);

	public Directory selectNovelByName(String novelName);

	public String selectAuthorById(int nId);

	public void updateAuthorById(Directory directory);

	public List<HashMap<String, Object>> selectByKeyWord(String novelName);

	public void updateLastTimeById(Directory novelInfo);

	public void updateNovelInfo(Directory novelInfo);

	public void batchAddDirectory(List<Directory> directoryList);

	List<HashMap<String, Object>> selectAllByPage(int currentIndex, int pageSize);

	public int getNovelCount();
}
