package com.hcjava.dao;

import java.util.List;

import com.hcjava.pojo.Note;

public interface NoteDao {

	List<Note> findByBookId(String bookId);//查看笔记列表
	
	Note findByNoteId(String NoteId);//查看笔记内容
	
	int update(Note note);//编辑笔记内容
	
	int save(Note note);
}
