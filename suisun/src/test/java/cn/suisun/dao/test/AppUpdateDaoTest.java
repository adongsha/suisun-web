package cn.suisun.dao.test;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import cn.suisun.beans.AppUpdate;
import cn.suisun.dao.AppUpdateDao;
import cn.suisun.utils.spring.SpringBeanFactory;

public class AppUpdateDaoTest {
	
	AppUpdateDao appUpdateDao;
	@Before
	public void init() {
		appUpdateDao = SpringBeanFactory.getBean(AppUpdateDao.class);
	}
	
	@Test
	public void save(){
		AppUpdate a = new AppUpdate();
		a.setAppPlatform("mobile");
		a.setCreateTime(new Date().toString());
		a.setDownloadUrl("http://115.28.175.215");
		a.setMd5("adfsadf");
		a.setUpdateContent("我要更新");
		a.setVersionCode("1.0");
		appUpdateDao.save(a);
	}
}
