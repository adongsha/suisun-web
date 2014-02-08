package cn.suisun.dao.test;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import cn.suisun.beans.CustomerStat;
import cn.suisun.dao.CustomerStatDao;
import cn.suisun.utils.spring.SpringBeanFactory;

public class CustomStatDaoTest {

	CustomerStatDao customerStatDao;

	@Before
	public void init() {
		customerStatDao = SpringBeanFactory.getBean(CustomerStatDao.class);
	}

	@Test
	public void initDate() {
		for (int i = 0; i < 13; i++) {
             CustomerStat cs = new CustomerStat();
             cs.setAlbumLook(i+"-album");
             cs.setAlbumType("mobile");
             cs.setArea("佛山");
             cs.setDownloadTime(new Date());
             cs.setLastTimeLook(new Date());
             cs.setPhoneType("iphone");
             customerStatDao.save(cs);
             try {
				Thread.sleep(1000*3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
