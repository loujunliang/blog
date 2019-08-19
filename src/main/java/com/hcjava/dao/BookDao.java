package com.hcjava.dao;

import java.util.List;

import com.hcjava.pojo.Book;

public interface BookDao {

	List<Book> findByUserId(String uid);
	
	void save(Book book);
}
