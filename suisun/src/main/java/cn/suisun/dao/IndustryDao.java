package cn.suisun.dao;

import java.io.Serializable;
import java.util.List;

import cn.suisun.beans.Industry;

public interface IndustryDao {

	public Serializable save(Industry industry);
	
	public List<Industry> getIndustryListByPage(int currentPage,int pageSize);
	
	public int getIndustryListByPageAmount();
	
	public void update(Industry industry);
	
	public void delete(Industry industry);
	
	public List<Industry> getIndustry();
	
	public Industry getIndustryById(String id);
	
}
