package com.hcjava.dao;

import java.util.List;

import com.hcjava.pojo.Note;

public interface NoteDao {

	List<Note> findByBookId(String bookId);//�鿴�ʼ��б�
	
	Note findByNoteId(String NoteId);//�鿴�ʼ�����
	
	int update(Note note);//�༭�ʼ�����
	
	int save(Note note);
}
