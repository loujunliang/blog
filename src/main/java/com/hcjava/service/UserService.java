package com.hcjava.service;

import com.hcjava.pojo.User;
import com.hcjava.util.NoteResult;

public interface UserService {

	NoteResult checkLogin(String name,String password);
	
	NoteResult save(User user);
	
	NoteResult updatePassword(String userId, String last_password, String new_password);
}
