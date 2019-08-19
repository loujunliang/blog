package com.hcjava.service;

import com.hcjava.pojo.Note;
import com.hcjava.util.NoteResult;

public interface NoteService {

	/**
	 * 获取笔记列表
	 * @param bookId
	 * @return
	 */
	NoteResult loadNotes(String bookId);
	
	/**
	 * 获取笔记内容
	 * @param noteId
	 * @return
	 */
	NoteResult loadNote(String noteId);
	
	/**
	 * 修改笔记内容
	 * @param note
	 * @return
	 */
	NoteResult update(Note note);
}
