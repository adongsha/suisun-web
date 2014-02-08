package cn.suisun.dao.test;

import org.junit.Before;
import org.junit.Test;

import cn.suisun.beans.User;
import cn.suisun.dao.UserDao;
import cn.suisun.utils.spring.SpringBeanFactory;


public class UserDaoTest {
	
	UserDao userDao;

	@Before
	public void init(){
		userDao = SpringBeanFactory.getBean(UserDao.class);
		System.out.println("userDao:"+userDao);
	}
	
	@Test
	public void test(){
		for(int i=0; i<50; i++){
			User user = new User();
			user.setAccount(i+"-admin");
			user.setPassword(i+"-admin");
			user.setPower(0);
			userDao.save(user);
			
		}
	}
}
