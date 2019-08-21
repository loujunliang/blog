package com.hcjava.service;

import com.hcjava.pojo.Note;
import com.hcjava.util.NoteResult;

public interface NoteService {

	/**
	 * 	��ȡ�ʼ��б�
	 * @param bookId
	 * @return
	 */
	NoteResult loadNotes(String bookId);
	
	/**
	 * 	��ȡ�ʼ�����
	 * @param noteId
	 * @return
	 */
	NoteResult loadNote(String noteId);
	
	/**
	 * 	�޸ıʼ�����
	 * @param note
	 * @return
	 */
	NoteResult update(Note note);
	
	/**
	 * 	��ӱʼ�
	 * @param note
	 * @return
	 */
	NoteResult save(Note note);
	
	/**
	 * 	�ƶ��ʼ�
	 * @param note
	 * @return
	 */
	NoteResult updateMove(Note note);
	
	/**
	 * 	�߼�ɾ��
	 * @param noteId
	 * @return
	 */
	NoteResult delete(String noteId);
	
	/**
	 * 	��ѯ����վ�ʼ�
	 * @param userId
	 * @return
	 */
	NoteResult findByStatusId(String userId);
	
	/**
	 * 	�ָ��ʼ�
	 * @param noteId
	 * @return
	 */
	NoteResult replay(String noteId, String bookId);
}
