package com.trojan.socket;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.trojan.controller.MainService;
import com.trojan.controller.ReptileController;
import com.trojan.entity.Directory;
import com.trojan.service.CategoryService;
import com.trojan.service.ChapterService;
import com.trojan.service.DirectoryService;
import com.trojan.util.DocUtils;

@Controller
public class GetNovelThread extends MainService implements Runnable {

	public final static String TARGETURL = "http://www.xbiquge.la";
	@Resource
	private DirectoryService directoryService;
	@Resource
	private CategoryService categoryService;

	public GetNovelThread() {
	}

	public GetNovelThread(DirectoryService directoryService, CategoryService categoryService,
			ChapterService chapterService) {
		this.directoryService = directoryService;
		this.categoryService = categoryService;
		this.chapterService = chapterService;
	}

	@Override
	public void run() {
		String url = TARGETURL + "/xiaoshuodaquan/";
		// 获取目标网页内容
		Document doc = DocUtils.getDoc(url);
		if (doc == null) {
		} else {
			Elements novelList = doc.select(".novellist");
			List<Directory> directoryList = new ArrayList<Directory>();

			// 循环类别
			for (Element nl : novelList) {

				Elements novel = nl.select("li");

				// 循环列表
				for (Element nov : novel) {
					// 小说名称
					String novelName = nov.text();

					// 路径
					String novelURL = nov.select("a").attr("href");
					Directory directory = new Directory();
					directory.setnName(novelName);
					directory.setnURL(novelURL);
					directory.setnLURL(novelURL);
					directoryList.add(directory);
				}
			}
			directoryService.batchAddDirectory(directoryList);
		}
	}

}
