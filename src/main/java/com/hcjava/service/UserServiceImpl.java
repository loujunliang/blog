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
		// �ж��û����Ƿ����
		if (user == null) {
			result.setStatus(1);
			result.setMsg("�û�������");
			return result;
		}
		// �ж��û����Ƿ����
		String md5 = NoteUtil.md5(password);
		if (!user.getCn_user_password().equals(md5)) {
			result.setStatus(2);
			result.setMsg("�������");
			return result;
		}
		// ��¼�ɹ�
		result.setStatus(0);
		result.setMsg("��¼�ɹ�");
		user.setCn_user_password("");//�������룬������
		result.setData(user);
		return result;
	}

}
