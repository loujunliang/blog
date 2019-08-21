package com.hcjava.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcjava.pojo.Note;
import com.hcjava.service.NoteService;
import com.hcjava.util.NoteResult;

@Controller
@RequestMapping("/note")
public class NoteController {

	@Resource
	private NoteService noteService;
	
	@RequestMapping("/loadnotes.do")
	@ResponseBody
	public NoteResult loadNotes(String bookId) {
		NoteResult result = noteService.loadNotes(bookId);
		return result;
	}
	
	@RequestMapping("/load.do")
	@ResponseBody
	public NoteResult loadNote(String noteId) {
		NoteResult result = noteService.loadNote(noteId);
		return result;
	}
	
	@RequestMapping("/update.do")
	@ResponseBody
	public NoteResult update(Note note) {
		NoteResult result = noteService.update(note);
		return result;
	}
	
	@RequestMapping("/add.do")
	@ResponseBody
	public NoteResult add(Note note) {
		NoteResult result = noteService.save(note);
		return result;
	}
	
	@RequestMapping("/move.do")
	@ResponseBody
	public NoteResult updateMove(Note note) {
		NoteResult result = noteService.updateMove(note);
		return result;
	}
	
	@RequestMapping("/delete.do")
	@ResponseBody
	public NoteResult delete(String noteId) {
		NoteResult result = noteService.delete(noteId);
		return result;
	}
	
	@RequestMapping("/rollback.do")
	@ResponseBody
	public NoteResult rollBackNote(String userId) {
		NoteResult result = noteService.findByStatusId(userId);
		return result;
	}
	
	@RequestMapping("/replay.do")
	@ResponseBody
	public NoteResult replay(String noteId, String bookId) {
		NoteResult result = noteService.replay(noteId,bookId);
		return result;
	}
}
