package com.hcjava.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcjava.pojo.User;
import com.hcjava.service.UserService;
import com.hcjava.util.NoteResult;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userService;

	@RequestMapping("/login.do")
	@ResponseBody
	public NoteResult login(String name, String password) {
		NoteResult result = userService.checkLogin(name, password);
		return result;
	}

	@RequestMapping("/add.do")
	@ResponseBody
	public NoteResult add(User user) {
		NoteResult result = userService.save(user);
		return result;
	}
	
	@RequestMapping("/change.do")
	@ResponseBody
	public NoteResult change(String userId, String last_password, String new_password) {
		NoteResult result = userService.updatePassword(userId, last_password, new_password);
		return result;
	}

}
