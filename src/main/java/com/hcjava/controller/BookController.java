package com.hcjava.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcjava.service.BookService;
import com.hcjava.util.NoteResult;

@Controller
@RequestMapping("/book")
public class BookController {

	@Resource
	private BookService bookService;
	
	@RequestMapping("/loadbooks.do")
	@ResponseBody
	public NoteResult loadBooks(String uid) {
		NoteResult result = bookService.loadUserBooks(uid);
		return result;
	}
}
