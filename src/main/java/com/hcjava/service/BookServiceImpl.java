package com.hcjava.service;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hcjava.dao.BookDao;
import com.hcjava.pojo.Book;
import com.hcjava.util.NoteResult;
import com.hcjava.util.NoteUtil;

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

	public NoteResult save(Book book) {
		NoteResult result = new NoteResult();
		String uuid = NoteUtil.createUUID().replace("-", "");
		book.setCn_notebook_id(uuid);
		book.setCn_notebook_type_id("1");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		book.setCn_notebook_createtime(timestamp);
		int i = bookDao.save(book);
		if(i == 1) {
			result.setStatus(0);
			result.setMsg("添加笔记本成功");
			result.setData(book);
			return result;
		}else {
			result.setStatus(1);
			result.setMsg("添加笔记本失败");
			return result;
		}
	}

}
