package cn.suisun.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.suisun.beans.CustomerStat;
import cn.suisun.dao.CustomerStatDao;
import cn.suisun.service.CustomerStatService;

@Service("CustomerStatService")
public class CustomerStatServiceImpl implements CustomerStatService{

	@Resource
	CustomerStatDao customerStatDao;
	
	
	public List<CustomerStat> getCustomerStatByPage(int currentPage,
			int pageSize, String albumType, String album, int orderBy) {
		return customerStatDao.getCustomerStatByPage(currentPage, pageSize, albumType, album, orderBy);
	}

	
	public int getCustomerStatAmount(String albumType, String album, int orderBy) {
		return customerStatDao.getCustomerStatAmount(albumType, album, orderBy);
	}

	
	public List<CustomerStat> getCustomerStatByPageById(int currentPage,
			int pageSize, String id, String albumType, String album, int orderBy) {
		
		return customerStatDao.getCustomerStatByUserId(currentPage, pageSize, id, albumType, album, orderBy);
	}

	
	public int getCustomerStatAmountById(String id, String albumType,
			String album, int orderBy) {
		return customerStatDao.getCustomerStatByIdAmount(id, albumType, album, orderBy);
	}

	
	
}
