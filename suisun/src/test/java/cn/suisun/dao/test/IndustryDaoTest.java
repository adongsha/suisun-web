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
			switch (i) {
			case 1:
				in.setIndustryName("家具");
				break;
			case 2:
				in.setIndustryName("五金");
				break;
			case 3:
				in.setIndustryName("服装");
				break;
			case 4:
				in.setIndustryName("水果");
				break;
			case 5:
				in.setIndustryName("蔬菜");
				break;
			case 6:
				in.setIndustryName("房地产");
				break;
			case 7:
				in.setIndustryName("IT");
				break;
			case 8:
				in.setIndustryName("餐饮");
				break;
			case 9:
				in.setIndustryName("食品");
				break;	
			case 10:
				in.setIndustryName("电器");
				break;	
			default:
				in.setIndustryName(i+"家具");
				break;
			}
			in.setNote(i+"备注：xxx");
			industryDao.save(in);
		}
	}
}
