package com.hcjava.service;

import com.hcjava.pojo.Note;
import com.hcjava.util.NoteResult;

public interface NoteService {

	/**
	 * 	获取笔记列表
	 * @param bookId
	 * @return
	 */
	NoteResult loadNotes(String bookId);
	
	/**
	 * 	获取笔记内容
	 * @param noteId
	 * @return
	 */
	NoteResult loadNote(String noteId);
	
	/**
	 * 	修改笔记内容
	 * @param note
	 * @return
	 */
	NoteResult update(Note note);
	
	/**
	 * 	添加笔记
	 * @param note
	 * @return
	 */
	NoteResult save(Note note);
	
	/**
	 * 	移动笔记
	 * @param note
	 * @return
	 */
	NoteResult updateMove(Note note);
	
	/**
	 * 	逻辑删除
	 * @param noteId
	 * @return
	 */
	NoteResult delete(String noteId);
	
	/**
	 * 	查询回收站笔记
	 * @param userId
	 * @return
	 */
	NoteResult findByStatusId(String userId);
	
	/**
	 * 	恢复笔记
	 * @param noteId
	 * @return
	 */
	NoteResult replay(String noteId, String bookId);
}
