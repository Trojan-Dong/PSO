package com.trojan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trojan.entity.Category;

@Controller
public class CategoryController extends MainService{


	@RequestMapping("addCategory")
	@ResponseBody
	public Category addCategory() {
		Category category = new Category();
		category.setcName("未分类");
		categoryService.addCategory(category);
		return category;
	}

	@RequestMapping("selectCateName")
	@ResponseBody
	public Category selectCategoryByName() {
		String novelName="未分类";
		Category category =categoryService.selectCateByName(novelName);
		return category;
		
	}
}
