package cn.suisun.dao.test;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.suisun.beans.Industry;
import cn.suisun.beans.User;
import cn.suisun.dao.IndustryDao;
import cn.suisun.dao.UserDao;
import cn.suisun.utils.spring.SpringBeanFactory;


public class UserDaoTest {
	
	UserDao userDao;
	IndustryDao industryDao;

	@Before
	public void init(){
		userDao = SpringBeanFactory.getBean(UserDao.class);
		industryDao = SpringBeanFactory.getBean(IndustryDao.class);
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
	
	@Test
	public void test1(){
		
		List<Industry> list = industryDao.getIndustry();
		for(int i=0; i<10; i++){
			User user = new User();
			user.setAccount("user"+i);
			user.setAddress("广州市-user"+i);
			user.setEmail("user"+i+"@gmail.com");
			user.setEnglishLinkMan("englistLinkMan-user"+i);
			user.setEnterpriseEnglish("EnterpriseEnglish-user"+i);
			user.setEnterpriseInfo("enterpriseInfo-user"+i);
			user.setEnterpriseName("enterpriseName-user"+i);
			user.setFax("fax-user"+i);
			user.setIndustryId(list.get(i).getUuid());
			user.setIp("100.190.190.1-user"+i);
			user.setLastTime(new Date());
			if(i%2 == 0){
				user.setLogoUrl("pic1.jpg");
			} else {
				user.setLogoUrl("pic2.jpg");
			}
			user.setPassword("user"+i);
			user.setPhone("phone-user"+i);
			user.setPower(2);
			user.setRegistDate(new Date());
			user.setShortEnglish("shortEnglish-user"+i);
			user.setTelephone("telephone-user"+i);
			user.setWebsite("website-uesr"+i);
			userDao.save(user);
		}
	}
}
