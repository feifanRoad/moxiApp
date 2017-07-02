package com.moxi.test.applicationTest;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.logicalcobwebs.proxool.ProxoolDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.moxi.dao.user.IUserDao;
import com.moxi.model.User;

public class ApplicationTest {

	@Test
	public void testApplication(){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
//		SqlSessionFactory sessionFactory = context.getBean(SqlSessionFactory.class);
//		
//		//System.out.println(sessionFactory);
//		SqlSession session = sessionFactory.openSession(true);
//		List<User> users = session.selectList("com.moxi.dao.user.IUserDao.findUsers");
//		for (User user : users) {
//			//System.out.println(user.getPhonenumber());
//			System.out.println(user.getUsername()+"===="+user.getAccount());
//		}
//		session.close();
		
//		IProfitDao profitDao = (IProfitDao) context.getBean("IProfitDao");
//		List<HashMap<String, Object>> lists = profitDao.findProfits();
//		System.out.println(lists);
		
//		IUserDao userDao = (IUserDao) context.getBean("IUserDao");
//		User user = new User();
//		user.setUsername("heitu");
//		user.setPassword("123456");
//		user.setAccount("黑土");
//		userDao.saveUser(user);
//		System.err.println(user.getAccount());
//		List<User> users = userDao.findUsers();
//		for (User user : users) {
//			System.out.println(user.getUsername());
//		}
		
//		ProxoolDataSource dataSource = context.getBean(ProxoolDataSource.class);
//		try {
//			Connection con = dataSource.getConnection();
//			System.out.println(con);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
	}
}
