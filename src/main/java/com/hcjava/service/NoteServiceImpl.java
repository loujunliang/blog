package com.hcjava.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hcjava.dao.NoteDao;
import com.hcjava.pojo.Note;
import com.hcjava.util.NoteResult;

@Service
public class NoteServiceImpl implements NoteService {

	@Resource
	private NoteDao noteDao;

	public NoteResult loadNotes(String bookId) {
		NoteResult result = new NoteResult();
		List<Note> list = noteDao.findByBookId(bookId);
		result.setStatus(0);
		result.setMsg("��ѯ�ʼ����");
		result.setData(list);
		return result;
	}

	public NoteResult loadNote(String noteId) {
		NoteResult result = new NoteResult();
		Note note = noteDao.findByNoteId(noteId);
		result.setStatus(0);
		result.setMsg("��ѯ�ʼ��������");
		result.setData(note);
		return result;
	}

	public NoteResult update(Note note) {
		NoteResult result = new NoteResult();
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		int i = noteDao.update(note);
		if (i == 1) {
			result.setStatus(0);
			result.setMsg("����ʼǳɹ�");
			return result;
		} else {
			result.setStatus(1);
			result.setMsg("��������ʧ��");
			return result;
		}
	}

}
