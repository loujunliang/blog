package com.hcjava.dao;

import java.util.List;

import com.hcjava.pojo.Book;

public interface BookDao {

	List<Book> findByUserId(String uid);//查询笔记本列表
	
	int save(Book book);
}
