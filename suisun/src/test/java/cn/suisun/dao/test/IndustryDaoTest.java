package cn.suisun.dao.test;

import org.junit.Test;

import cn.suisun.beans.Industry;
import cn.suisun.dao.IndustryDao;
import cn.suisun.utils.spring.SpringBeanFactory;

public class IndustryDaoTest {

	IndustryDao industryDao;
	
	@org.junit.Before
	public void init(){
		industryDao = SpringBeanFactory.getBean(IndustryDao.class);
	}
	
	@Test
	public void testSave(){
		for(int i=0; i<11; i++){
			Industry in = new Industry();
			in.setIndustryName(i+"-家具");
			in.setNote(i+"---------家具家具家具");
			industryDao.save(in);
		}
	}
}
