package com.hcjava.dao;

import com.hcjava.pojo.User;
/**
 * ��װUserģ������ݿ�Ĳ���
 * @author huachuang
 *
 */
public interface UserDao {

	User findByName(String name);//��¼���
	
	void save(User user);//ע��
	
	User findById(String userId);//��ѯ������
	
	int update(User user);//�޸�����
}
