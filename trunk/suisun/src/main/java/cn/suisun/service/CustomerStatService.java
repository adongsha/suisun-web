package cn.suisun.service;

import java.util.List;

import cn.suisun.beans.CustomerStat;

public interface CustomerStatService {

	public List<CustomerStat> getCustomerStatByPage(int currentPage,
			int pageSize, String albumType, String album, int orderBy);
	
	public int getCustomerStatAmount( String albumType, String album, int orderBy);
	
	public List<CustomerStat> getCustomerStatByPageById(int currentPage,
			int pageSize,String id, String albumType, String album, int orderBy);
	
	public int getCustomerStatAmountById(String id, String albumType, String album, int orderBy);
}
