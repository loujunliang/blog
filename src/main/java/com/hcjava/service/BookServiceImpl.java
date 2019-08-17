package com.hcjava.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hcjava.dao.BookDao;
import com.hcjava.pojo.Book;
import com.hcjava.util.NoteResult;

@Service
public class BookServiceImpl implements BookService {

	@Resource
	private BookDao bookDao;

	public NoteResult loadUserBooks(String uid) {
		NoteResult result = new NoteResult();
		List<Book> list = bookDao.findByUserId(uid);
		result.setStatus(0);
		result.setMsg("");
		result.setData(list);
		return result;
	}

}
