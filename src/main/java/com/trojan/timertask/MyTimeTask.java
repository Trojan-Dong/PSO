package com.trojan.timertask;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.trojan.controller.MainService;
import com.trojan.controller.ReptileController;
import com.trojan.entity.Category;
import com.trojan.entity.Chapter;
import com.trojan.entity.Directory;
import com.trojan.socket.GetChapterListThread;
import com.trojan.socket.GetNovelThread;
import com.trojan.util.DocUtils;


@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling
public class MyTimeTask extends MainService{
	public final static String TARGETURL = "http://www.xbiquge.la";
	/**
	 * 初始获取小说
	 * 
	 * @return
	 */
	@Scheduled(initialDelay = 30*60*60*1000,fixedRate = 30*60*60*1000)
	public void getNovel() {
		logger.info("--getNovel run----");
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
//					try {
//						getChapterList(novelURL);
//					} catch (Exception e) {
//						logger.error("获取章节错误");
//						e.printStackTrace();
//					}
				}
			}
			directoryService.batchAddDirectory(directoryList);
		}
		logger.debug("--getNovel end----");
	}
	
	@Scheduled(initialDelay = 0*60*1000,fixedRate = 5*60*1000)
	public void updateBookInBookshelf() {
		logger.debug("updateBookInBookshelf run");
		logger.debug("刷新书架");
		List<HashMap<String, Object>> bookList = bookshelfService.selectAll();
		for (HashMap<String, Object> book : bookList) {
			getChapterList(book.get("nURL").toString());
		}
		logger.debug("updateBookInBookshelf end");
	}

	
	public void getChapterList(String url) {
		logger.debug("--getChapterList run----");
		if (!url.startsWith(TARGETURL)) {
			url = TARGETURL + url;
		}
		// 页面信息
		JSONObject pageInfo = new JSONObject();
		// 获取目标网页内容
		Document doc = DocUtils.getDoc(url);
		if (doc == null) {
			throw new RuntimeException("no doc exist");
		}
		Elements chapterListInfo = doc.select("#list a");

		// 获取小说名称
		String novelName = doc.select("#info h1").text();
		// 从数据库中获取当前小说的信息
		Directory novelInfo = directoryService.selectNovelByName(novelName);
		// 获取当前小说的id
		int nId = novelInfo.getnId();

		// 获取小说头部信息
		Elements infos = doc.select("#info p").select("p");

		// 作者
		String authorInfo = infos.get(0).text();
		String author = authorInfo.substring(authorInfo.indexOf("：") + 1, authorInfo.length());
		pageInfo.put("author", author);
		novelInfo.setAuthor(author);

		// 最后更新时间
		String timeInfo = infos.get(2).text();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date lastUpdateTime;
		try {
			lastUpdateTime = simpleDateFormat.parse(timeInfo.substring(timeInfo.indexOf("：") + 1, timeInfo.length()));
			novelInfo.setLastUpdateTime(lastUpdateTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// 简介
		Elements intros = doc.select("#intro");
		String intro = intros.select("p").remove(1).text();
		if(intro.length()>125){
			intro=intro.substring(0,125)+"...";
		}
		novelInfo.setIntroduction(intro);

		// 章节信息
		List<Chapter> chapterInfo = new ArrayList<Chapter>();
		String coverPic = doc.select("#fmimg img").attr("src");
		novelInfo.setCoverPic(coverPic);

		// 类别
		String cateName = doc.select(".con_top").select("a").last().text();
		Category cate = categoryService.selectCateByName(cateName);
		// 如果类别为空新增类别信息
		if (cate == null) {
			cate = new Category();
			cate.setcName(cateName);
			categoryService.addCategory(cate);
			cate = categoryService.selectCateByName(cateName);
		}
		novelInfo.setcId(cate.getcId());

		// 更新小说信息
		directoryService.updateNovelInfo(novelInfo);
		// 章节
		List<Chapter> chapterList = new ArrayList<Chapter>();
		for (Element element : chapterListInfo) {
			String chapterUrl = "http://www.xbiquge.la" + element.select("a").attr("href");
			String chapterName = element.select("a").html();
			Chapter chapter = new Chapter();
			chapter.setChapterName(chapterName);
			chapter.setChapterUrl(chapterUrl);
			chapter.setnId(nId);
			chapterList.add(chapter);
			chapterInfo.add(chapter);
		}

		if (chapterList.size() > 0) {
			chapterService.batchAddChapter(chapterList);
		}
		pageInfo.put("novelInfo", novelInfo);
		pageInfo.put("chapterInfo", chapterInfo);
		logger.debug("--getChapterList end----");

	}
	
}
