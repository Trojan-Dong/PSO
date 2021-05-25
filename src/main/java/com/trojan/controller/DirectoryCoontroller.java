package com.trojan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trojan.entity.Directory;

@Controller
public class DirectoryCoontroller extends MainService {

	@RequestMapping("/testSelect")
	@ResponseBody
	public Directory testSelect() {
		logger.debug("--test run----");
		int id = 1;
		Directory directory = directoryService.selectById(id);
		logger.debug(directory + "111");
		return directory;
	}

	@RequestMapping("/testInsert")
	@ResponseBody
	public Directory testInsert() {
		logger.debug("--test run----");
		Directory directory = new Directory();
		directory.setcId(1);
		directory.setnName("不可说");
		directory.setnURL("ssdfadad");
		directoryService.addNovel(directory);
		return directory;
	}
}
