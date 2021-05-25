package com.trojan.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.trojan.entity.Bookshelf;
import com.trojan.mapper.BookshelfMapper;

@Service
public class BookshelfService implements BookshelfMapper {
	@Resource
	private BookshelfMapper bookshelfMapper;

	@Override
	public Bookshelf findById(int id) {
		Bookshelf bookshelf = bookshelfMapper.findById(id);
		return bookshelf;
	}

	@Override
	public List<HashMap<String, Object>> findByUserId(int userId) {
		List<HashMap<String, Object>> bookList = bookshelfMapper.findByUserId(userId);
		return bookList;
	}

	@Override
	public void addBook(Bookshelf bookshelf) {
		bookshelfMapper.addBook(bookshelf);
	}

	@Override
	public Bookshelf findByUserIdAndNovelId(int userId, int novelId) {
		Bookshelf bookshelf = bookshelfMapper.findByUserIdAndNovelId(userId, novelId);
		return bookshelf;
	}

	@Override
	public void updateBookInfo(Bookshelf bookshelf) {
		bookshelfMapper.updateBookInfo(bookshelf);
		
	}
	@Override
	public void removeBookInfo(Bookshelf bookshelf) {
		bookshelfMapper.removeBookInfo(bookshelf);
		
	}
	@Override
	public List<HashMap<String, Object>> selectAll() {
		List<HashMap<String, Object>> bookList=bookshelfMapper.selectAll();
		return bookList;
	}

	@Override
	public HashMap testSql() {
		HashMap map=bookshelfMapper.testSql();
		return map;
	}

}
