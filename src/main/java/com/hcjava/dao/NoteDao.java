package com.hcjava.dao;

import java.util.List;

import com.hcjava.pojo.Note;

public interface NoteDao {

	List<Note> findByBookId(String bookId);
	
	Note findByNoteId(String NoteId);
	
	int update(Note note);
}