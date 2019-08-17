package com.hcjava.service;

import com.hcjava.util.NoteResult;

public interface NoteService {

	NoteResult loadNotes(String bookId);
}
