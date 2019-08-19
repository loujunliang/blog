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
}
