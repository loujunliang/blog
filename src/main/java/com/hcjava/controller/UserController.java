package com.hcjava.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcjava.service.UserService;
import com.hcjava.util.NoteResult;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userServcie;

	@RequestMapping("/login.do")
	@ResponseBody
	public NoteResult login(String name, String password) {
		NoteResult result = userServcie.checkLogin(name, password);
		return result;
	}
}
