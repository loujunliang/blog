package com.hcjava.service;

import com.hcjava.pojo.Book;
import com.hcjava.util.NoteResult;

public interface BookService {

	NoteResult loadUserBooks(String uid);//��ѯ�ʼǱ�
	
	NoteResult save(Book book);
}
