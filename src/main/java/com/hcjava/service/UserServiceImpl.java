package com.hcjava.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcjava.dao.UserDao;
import com.hcjava.pojo.User;
import com.hcjava.util.NoteException;
import com.hcjava.util.NoteResult;
import com.hcjava.util.NoteUtil;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;

	// 登录
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
		String md5;
		try {
			md5 = NoteUtil.md5(password);
			if (!user.getCn_user_password().equals(md5)) {
				result.setStatus(2);
				result.setMsg("密码错误");
				return result;
			}
		} catch (Exception e) {
			throw new NoteException("密码加密异常", e);
		}
		// 登录成功
		result.setStatus(0);
		result.setMsg("登录成功");
		user.setCn_user_password("");// 屏蔽密码，不返回
		result.setData(user);
		return result;
	}

	// 注册
	public NoteResult save(User user) {
		NoteResult result = new NoteResult();
		// 检查用户名是否存在
		User user1 = userDao.findByName(user.getCn_user_name());
		if (user1 != null) {
			result.setStatus(1);
			result.setMsg("用户名已存在");
			return result;
		}
		// 执行注册
		// 使用工具类生成随机id
		String id = NoteUtil.createUUID();
		user.setCn_user_id(id);// 设置用户id
		try {
			// 使用工具类加密密码
			String md5 = NoteUtil.md5(user.getCn_user_password());
			user.setCn_user_password(md5);// 设置密码
			userDao.save(user);
			result.setStatus(0);
			result.setMsg("注册成功");
			result.setData(user);
			return result;
		} catch (Exception e) {
			throw new NoteException("注册异常", e);
		}
	}

	@Transactional
	public NoteResult updatePassword(String userId, String last_password, String new_password) {
		NoteResult result = new NoteResult();
		User user = userDao.findById(userId);
		String string = null;
		try {
			string = NoteUtil.md5(last_password);
			if(!string.equals(user.getCn_user_password())) {
				result.setStatus(1);
				result.setMsg("原始密码错误");
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			User user2 = new User();
			user2.setCn_user_id(userId);
			String md5 = NoteUtil.md5(new_password);
			user2.setCn_user_password(md5);
			userDao.update(user2);
			result.setStatus(0);
			result.setMsg("密码修改成功");
			return result;
		} catch (Exception e) {
			throw new NoteException("密码加密异常", e);
		}
	}

}
