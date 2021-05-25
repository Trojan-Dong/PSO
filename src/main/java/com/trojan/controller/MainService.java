package com.trojan.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trojan.service.BookshelfService;
import com.trojan.service.CategoryService;
import com.trojan.service.ChapterService;
import com.trojan.service.DirectoryService;
import com.trojan.service.UserService;

public class MainService {
	public Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	public CategoryService categoryService;
	@Resource
	public DirectoryService directoryService;
	@Resource
	public ChapterService chapterService;
	@Resource
	public BookshelfService bookshelfService;
	@Resource
	public UserService userService;
}
