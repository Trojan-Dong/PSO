package com.trojan.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.trojan.entity.Category;
import com.trojan.entity.Chapter;

@Mapper
public interface ChapterMapper {
	public Chapter selectById();

	public List<HashMap<String, Object>> selectAll();

	public void addChapter(Chapter chapter);

	public Chapter selectChapterByName(String chapterName);

	public List<HashMap<String, Object>> selectChapterByNid(int nId);

	public Integer selectChapterCountByNid(int nId);

	public void batchAddChapter(List<Chapter> chapterList);

	public Chapter findByChapterUrl(String chapterUrl);

	public int getChapterCount(int id);
}
