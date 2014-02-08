package cn.suisun.dao;

import java.io.Serializable;
import java.util.List;

import cn.suisun.beans.CustomerStat;

public interface CustomerStatDao {
	
	public Serializable save(CustomerStat customerStat);

	public List<CustomerStat> getCustomerStatByPage(int currentPage, 
			int pageSize,String albumType,String album,int orderBy);
	
	public int getCustomerStatAmount(String albumType,String album,int orderBy);
	
	public List<CustomerStat> getCustomerStatByUserId(int currentPage,int pageSize,String id,String albumType,String album,int orderBy);
	
	public int getCustomerStatByIdAmount(String id,String albumType,String album,int orderBy);
	
	
}
