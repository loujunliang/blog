package com.hcjava.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hcjava.dao.UserDao;
import com.hcjava.pojo.User;

public class TestCase {

	@Test
	public void testDao() {
		String[] cfg = {"conf/spring-mvc.xml","conf/spring-mybatis.xml"};
		ApplicationContext ac = new ClassPathXmlApplicationContext(cfg);
		UserDao bean = ac.getBean("userDao",UserDao.class);
		User user = bean.findByName("zhouj");
		System.out.println(user.getCn_user_password());
	}
	
}
