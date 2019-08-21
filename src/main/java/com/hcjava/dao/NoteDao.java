package com.hcjava.dao;

import java.util.List;

import com.hcjava.pojo.Note;

public interface NoteDao {

	List<Note> findByBookId(String bookId);//查看笔记列表
	
	Note findByNoteId(String NoteId);//查看笔记内容
	
	//编辑笔记内容  //逻辑删除笔记  //恢复笔记  //移动笔记
	int update(Note note);
	
	int save(Note note);//添加笔记
	
	List<Note> findByStatusId(String userId);//查询回收站笔记
	
}
