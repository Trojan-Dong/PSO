package com.trojan.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.trojan.entity.Chapter;
import com.trojan.entity.Directory;
import com.trojan.entity.Res;
import com.trojan.mapper.ChapterMapper;
import com.trojan.mapper.ChapterMapper;
import com.trojan.mapper.DirectoryMapper;
import com.trojan.mapper.ResMapper;

@Service
// service命名与userService重复
public class ChapterService implements ChapterMapper {

	@Resource
	private ChapterMapper chapterMapper;

	@Override
	public Chapter selectById() {
		Chapter chapter = chapterMapper.selectById();
		return chapter;
	}

	@Override
	public List<HashMap<String, Object>> selectAll() {
		List<HashMap<String, Object>> list = chapterMapper.selectAll();
		return list;
	}

	@Override
	public void addChapter(Chapter chapter) {
		chapterMapper.addChapter(chapter);
	}

	@Override
	public Chapter selectChapterByName(String chapterName) {
		Chapter chapter = chapterMapper.selectChapterByName(chapterName);
		return chapter;
	}

	@Override
	public List<HashMap<String, Object>> selectChapterByNid(int nId) {
		List<HashMap<String, Object>> chapterList = chapterMapper.selectChapterByNid(nId);
		return chapterList;
	}

	@Override
	public Integer selectChapterCountByNid(int nId) {
		Integer chapterCount = chapterMapper.selectChapterCountByNid(nId);
		return chapterCount;
	}

	@Override
	public void batchAddChapter(List<Chapter> chapterList) {
		chapterMapper.batchAddChapter(chapterList);

	}

	@Override
	public Chapter findByChapterUrl(String chapterUrl) {
		Chapter chapter = chapterMapper.findByChapterUrl(chapterUrl);
		return chapter;
	}

	@Override
	public int getChapterCount(int id) {
		int count=chapterMapper.getChapterCount(id);
		return count;
	}

}
