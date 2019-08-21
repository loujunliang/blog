package com.hcjava.dao;

import java.util.List;

import com.hcjava.pojo.Note;

public interface NoteDao {

	List<Note> findByBookId(String bookId);//�鿴�ʼ��б�
	
	Note findByNoteId(String NoteId);//�鿴�ʼ�����
	
	//�༭�ʼ�����  //�߼�ɾ���ʼ�  //�ָ��ʼ�  //�ƶ��ʼ�
	int update(Note note);
	
	int save(Note note);//��ӱʼ�
	
	List<Note> findByStatusId(String userId);//��ѯ����վ�ʼ�
	
}
