package com.trojan.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.trojan.entity.Category;
import com.trojan.entity.Directory;
import com.trojan.entity.Res;
import com.trojan.mapper.DirectoryMapper;
import com.trojan.mapper.ResMapper;

@Service
// service命名与userService重复
public class DirectoryService implements DirectoryMapper {

	@Resource
	private DirectoryMapper directoryMapper;

	@Override
	public Directory selectById(int id) {
		Directory directory = directoryMapper.selectById(id);
		return directory;
	}

	@Override
	public List<HashMap<String, Object>> selectAll() {
		List<HashMap<String, Object>> list = directoryMapper.selectAll();
		return list;
	}

	@Override
	public void addNovel(Directory directory) {
		directoryMapper.addNovel(directory);

	}

	@Override
	public Directory selectNovelByName(String novelName) {
		Directory directory = directoryMapper.selectNovelByName(novelName);
		return directory;
	}

	@Override
	public String selectAuthorById(int nId) {
		String author = directoryMapper.selectAuthorById(nId);
		return author;
	}

	@Override
	public void updateAuthorById(Directory directory) {
		directoryMapper.updateAuthorById(directory);
	}

	@Override
	public List<HashMap<String, Object>> selectByKeyWord(String novelName) {
		List<HashMap<String, Object>> list = directoryMapper.selectByKeyWord(novelName);
		return list;
	}

	@Override
	public void updateLastTimeById(Directory novelInfo) {
		directoryMapper.updateLastTimeById(novelInfo);

	}

	@Override
	public void updateNovelInfo(Directory novelInfo) {
		directoryMapper.updateNovelInfo(novelInfo);
	}

	@Override
	public void batchAddDirectory(List<Directory> directoryList) {
		directoryMapper.batchAddDirectory(directoryList);

	}

	@Override
	public List<HashMap<String, Object>> selectAllByPage(int currentIndex, int pageSize) {
		List<HashMap<String, Object>> list = directoryMapper.selectAllByPage(currentIndex, pageSize);
		return list;
	}

	@Override
	public int getNovelCount() {
		int count=directoryMapper.getNovelCount();
		return count;
	}
}
