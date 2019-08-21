package com.hcjava.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hcjava.dao.NoteDao;
import com.hcjava.pojo.Note;
import com.hcjava.util.NoteResult;
import com.hcjava.util.NoteUtil;

@Service
public class NoteServiceImpl implements NoteService {

	@Resource
	private NoteDao noteDao;

	public NoteResult loadNotes(String bookId) {
		NoteResult result = new NoteResult();
		List<Note> list = noteDao.findByBookId(bookId);
		result.setStatus(0);
		result.setMsg("查询笔记完毕");
		result.setData(list);
		return result;
	}

	public NoteResult loadNote(String noteId) {
		NoteResult result = new NoteResult();
		Note note = noteDao.findByNoteId(noteId);
		result.setStatus(0);
		result.setMsg("查询笔记内容完成");
		result.setData(note);
		return result;
	}

	public NoteResult update(Note note) {
		NoteResult result = new NoteResult();
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		int i = noteDao.update(note);
		if (i == 1) {
			result.setStatus(0);
			result.setMsg("保存笔记成功");
			return result;
		} else {
			result.setStatus(1);
			result.setMsg("保存内容失败");
			return result;
		}
	}

	public NoteResult save(Note note) {
		NoteResult result = new NoteResult();
		String uuid = NoteUtil.createUUID();
		note.setCn_note_id(uuid);//添加笔记id
		note.setCn_note_create_time(System.currentTimeMillis());//创建时间
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		note.setCn_note_status_id("1");
		note.setCn_note_type_id("1");
		note.setCn_note_body("");
		int i = noteDao.save(note);
		if(i == 1) {
			result.setStatus(0);
			result.setMsg("添加成功");
			result.setData(note);
			return result;
		}else {
			result.setStatus(1);
			result.setMsg("添加失败");
			return result;
		}
	}

	public NoteResult updateMove(Note note) {
		NoteResult result = new NoteResult();
		int i = noteDao.update(note);
		if(i == 1) {
			result.setStatus(0);
			result.setMsg("移动成功");
			result.setData(note);
			return result;
		}else {
			result.setStatus(1);
			result.setMsg("移动失败");
			return result;
		}
	}

	public NoteResult delete(String noteId) {
		NoteResult result = new NoteResult();
		Note note = new Note();
		note.setCn_note_id(noteId);
		note.setCn_note_status_id("2");
		int i = noteDao.update(note);
		if(i == 1) {
			result.setStatus(0);
			result.setMsg("删除成功");
			return result;
		}else {
			result.setStatus(1);
			result.setMsg("删除失败");
			return result;
		}
	}

	public NoteResult findByStatusId(String userId) {
		NoteResult result = new NoteResult();
		List<Note> list = noteDao.findByStatusId(userId);
		if(list == null) {
			result.setStatus(1);
			result.setMsg("获取数据失败");
			return result;
		}
		result.setStatus(0);
		result.setMsg("获取数据成功");
		result.setData(list);
		return result;
	}

	public NoteResult replay(String noteId, String bookId) {
		NoteResult result = new NoteResult();
		Note note = new Note();
		note.setCn_note_id(noteId);
		note.setCn_notebook_id(bookId);
		note.setCn_note_status_id("1");
		int i = noteDao.update(note);
		if(i > 0) {
			result.setStatus(0);
			result.setMsg("笔记恢复成功");
			return result;
		}else {
			result.setStatus(1);
			result.setMsg("笔记恢复失败");
			return result;
		}
	}
}
