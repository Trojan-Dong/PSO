package com.trojan.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.trojan.entity.Category;
import com.trojan.entity.Directory;
import com.trojan.service.CategoryService;
import com.trojan.service.DirectoryService;
import com.trojan.service.ResService;

@Controller
public class TestController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private ResService resService;
	@Resource
	private CategoryService categoryService;
	@Resource
	private DirectoryService directoryService;

	@RequestMapping("/test")
	@ResponseBody
	public List test() {
		logger.info("--test run----");

		List<HashMap<String, Object>> list = resService.selectAll();
		logger.info(list + "");
		JSONArray json = new JSONArray();
		for (HashMap<String, Object> map : list) {
			JSONObject jo = new JSONObject();
			jo.put("rid", map.get("rid"));
			jo.put("rname", map.get("rname"));
			jo.put("rsrc", map.get("rsrc"));
			jo.put("rprice", map.get("rprice"));
			json.add(jo);
		}
		return json;
	}

	@RequestMapping("/test01")
	@ResponseBody
	public Directory test01() {
		logger.info("--test run----");
		int id = 1;
		Directory directory = directoryService.selectById(id);
		logger.info(directory + "111");
		/*
		 * JSONArray json = new JSONArray(); for (HashMap<String, Object> map :
		 * directroy) { JSONObject jo = new JSONObject(); jo.put("rid",
		 * map.get("rid")); jo.put("rname", map.get("rname")); jo.put("rsrc",
		 * map.get("rsrc")); jo.put("rprice", map.get("rprice")); json.add(jo);
		 * }
		 */
		return directory;
	}

	@RequestMapping("/Doc")
	@ResponseBody
	public String docTest() {
		logger.info("--test run----");
		String url = "http://www.xbiquge.la/xiaoshuodaquan/";
		Document doc = null;
		try {
			doc = Jsoup.connect(url).post();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				doc = Jsoup.connect(url).get();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		logger.info(doc.toString());
		Elements novelList = doc.select(".novellist");
		Elements novel = novelList.select("li");
		for (Element element : novel) {
			System.out.println(element);
		}
		// Elements content=doc.select("div.read-content");
		// String nextPageURl =
		// "https"+doc.select("#j_chapterNext").attr("href");
		// Elements chapterName = doc.select(".j_chapterName");
		// logger.info(chapterName.toString());
		// logger.info(content.toString());
		// logger.info(nextPageURl);
		// return chapterName.toString()+content.toString();
		return novelList.toString();
	}

}
