package com.trojan.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.trojan.entity.Bookshelf;

@Mapper
public interface BookshelfMapper {

	public Bookshelf findById(int id);

	public List<HashMap<String,Object>> findByUserId(int userId);

	public void addBook(Bookshelf bookshelf);

	public Bookshelf findByUserIdAndNovelId(int userId, int novelId);

	public void removeBookInfo(Bookshelf bookshelf);

	public void updateBookInfo(Bookshelf bookshelf);

	public List<HashMap<String, Object>> selectAll();

	public HashMap testSql();

}
