package cn.suisun.service;

import java.io.Serializable;
import java.util.List;

import cn.suisun.beans.Industry;

public interface IndustryService {

	public List<Industry> getIndustryPage(int currentPage, int pageSize);
	
	public int getIndustryListByPageAmount();
	
	public List<Industry> getIndustry();
	
	public Serializable add(Industry industry);
	
	public void update(Industry industry);
	
	public void delete(Industry industry);
	
	public Industry getIndustryById(String id);
}
