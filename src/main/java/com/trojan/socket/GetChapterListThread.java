package com.trojan.socket;

import java.util.List;

import org.springframework.stereotype.Controller;

import com.trojan.entity.Chapter;
import com.trojan.service.ChapterService;

@Controller
public class GetChapterListThread implements Runnable {

	private ChapterService chapterService;
	private List<Chapter> chapterList;

	public GetChapterListThread() {
	}

	public GetChapterListThread(ChapterService chapterService) {
		this.chapterService = chapterService;
	}
	public GetChapterListThread(ChapterService chapterService,List<Chapter> chapterList) {
		this.chapterService = chapterService;
		this.chapterList=chapterList;
	}

	@Override
	public void run() {
		chapterService.batchAddChapter(chapterList);
	}

}
