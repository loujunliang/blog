package com.hcjava.service;

import com.hcjava.pojo.Note;
import com.hcjava.util.NoteResult;

public interface NoteService {

	/**
	 * ��ȡ�ʼ��б�
	 * @param bookId
	 * @return
	 */
	NoteResult loadNotes(String bookId);
	
	/**
	 * ��ȡ�ʼ�����
	 * @param noteId
	 * @return
	 */
	NoteResult loadNote(String noteId);
	
	/**
	 * �޸ıʼ�����
	 * @param note
	 * @return
	 */
	NoteResult update(Note note);
}
