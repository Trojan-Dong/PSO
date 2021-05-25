package com.trojan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trojan.entity.Chapter;

@Controller
public class ChapterController extends MainService{


	@RequestMapping("addChapter")
	@ResponseBody
	public Chapter addChapter() {
		Chapter chapter = new Chapter();
		chapter.setChapterName("未分类");
		chapterService.addChapter(chapter);
		return chapter;
	}

	@RequestMapping("selectChapterName")
	@ResponseBody
	public Chapter selectChapterByName() {
		String chapterName = "未分类";
		Chapter chapter = chapterService.selectChapterByName(chapterName);
		return chapter;

	}
}
