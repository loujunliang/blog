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

	// ��¼
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
		String md5;
		try {
			md5 = NoteUtil.md5(password);
			if (!user.getCn_user_password().equals(md5)) {
				result.setStatus(2);
				result.setMsg("�������");
				return result;
			}
		} catch (Exception e) {
			throw new NoteException("��������쳣", e);
		}
		// ��¼�ɹ�
		result.setStatus(0);
		result.setMsg("��¼�ɹ�");
		user.setCn_user_password("");// �������룬������
		result.setData(user);
		return result;
	}

	// ע��
	public NoteResult save(User user) {
		NoteResult result = new NoteResult();
		// ����û����Ƿ����
		User user1 = userDao.findByName(user.getCn_user_name());
		if (user1 != null) {
			result.setStatus(1);
			result.setMsg("�û����Ѵ���");
			return result;
		}
		// ִ��ע��
		// ʹ�ù������������id
		String id = NoteUtil.createUUID();
		user.setCn_user_id(id);// �����û�id
		try {
			// ʹ�ù������������
			String md5 = NoteUtil.md5(user.getCn_user_password());
			user.setCn_user_password(md5);// ��������
			userDao.save(user);
			result.setStatus(0);
			result.setMsg("ע��ɹ�");
			result.setData(user);
			return result;
		} catch (Exception e) {
			throw new NoteException("ע���쳣", e);
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
				result.setMsg("ԭʼ�������");
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
			result.setMsg("�����޸ĳɹ�");
			return result;
		} catch (Exception e) {
			throw new NoteException("��������쳣", e);
		}
	}

}
