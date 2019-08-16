package com.hcjava.dao;

import com.hcjava.pojo.User;
/**
 * 封装User模块对数据库的操作
 * @author huachuang
 *
 */
public interface UserDao {

	User findByName(String name);//登录检查
	
	void save(User user);//注册
}
