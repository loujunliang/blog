package com.hcjava.dao;

import java.util.List;

import com.hcjava.pojo.Book;

public interface BookDao {

	List<Book> findByUserId(String uid);//��ѯ�ʼǱ��б�
	
	int save(Book book);
}
