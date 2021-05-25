package com.trojan.controller;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class QiDianDemo extends MainService{

	// 抽取方法 传入URL 获得document对象
	public Document getDocument(String url) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = httpClient.execute(httpGet);
		String html = EntityUtils.toString(response.getEntity(), "utf-8");
		Document document = Jsoup.parse(html);
		return document;
	}

	@RequestMapping("/qd")
	@ResponseBody
	public void test(String[] args) throws Exception {

		logger.debug("test qd");
		// 起点首页url
		String url = "https://www.qidian.com/";
		// 获取document
		Document document = getDocument(url);
		// 获得某一榜单(这里为签约作家新书榜)
		Elements aEl = document.select("[class=rank-list mr0] li a[href*=book.qidian.com][class!=link]");
		logger.debug(document.toString());
		logger.debug(aEl.toString()+"\n\n");
		// 遍历获得的a标签 取出url 依次获得每本书的链接
		for (Element a : aEl) {

			url = "https:" + a.attr("href");
			document = getDocument(url);

			// 获得开始阅读的url
			Elements readBtn = document.select("#readBtn");
			String bookName = document.select(".book-info h1 em").text();
			url = "https:" + readBtn.attr("href");
			logger.debug(bookName);
			// 创建一个输出流,将爬到的小说以txt形式保存在硬盘
			BufferedWriter bw = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream("E://" + bookName + ".txt")));

			// 遍历某一本书的免费章节
			while (true) {
				document = getDocument(url);

				// 获得本章的章节名称 并输出到文本中
				Elements chapterName = document.select(".j_chapterName");
				bw.write(chapterName.text());
				bw.newLine();
				bw.flush();

				// 获得本章的小说内容 并输出到文本中
				Elements pEl = document.select("[class=read-content j_readContent] p");
				for (Element p : pEl) {
					bw.write(p.text());
					bw.newLine();
					bw.flush();
				}

				// 获得下一章的元素
				Elements chapterNext = document.select("#j_chapterNext[href*=read.qidian.com]");

				// 判断下一章是否存在(这里指的是免费章节)
				// 存在则继续进入下一章的链接
				// 不存在则跳出本书的章节遍历,进入榜单中下一本书的遍历
				if (chapterNext == null || chapterNext.size() == 0) {
					break;
				}

				// 获得下一章的链接
				url = "https:" + chapterNext.attr("href");
			}
			// 关流
			bw.close();

		}

	}
}
