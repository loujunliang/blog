package com.hcjava.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hcjava.dao.UserDao;
import com.hcjava.pojo.User;
import com.hcjava.util.NoteResult;
import com.hcjava.util.NoteUtil;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;

	public NoteResult checkLogin(String name, String password) {
		NoteResult result = new NoteResult();
		User user = userDao.findByName(name);
		// 判断用户名是否存在
		if (user == null) {
			result.setStatus(1);
			result.setMsg("用户不存在");
			return result;
		}
		// 判断用户名是否错误
		String md5 = NoteUtil.md5(password);
		if (!user.getCn_user_password().equals(md5)) {
			result.setStatus(2);
			result.setMsg("密码错误");
			return result;
		}
		// 登录成功
		result.setStatus(0);
		result.setMsg("登录成功");
		user.setCn_user_password("");//屏蔽密码，不返回
		result.setData(user);
		return result;
	}

}
