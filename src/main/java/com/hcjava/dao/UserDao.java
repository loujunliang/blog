package com.hcjava.dao;

import com.hcjava.pojo.User;
/**
 * ��װUserģ������ݿ�Ĳ���
 * @author huachuang
 *
 */
public interface UserDao {

	User findByName(String name);
}
