package cn.suisun.dao;

import java.io.Serializable;
import java.util.List;

import cn.suisun.beans.Recommend;

public interface RecommendDao {

	public List<Recommend> getRecommendPage(int currentPage, int pageSize);
	
	public int getRecommendPageAmount();
	
	public Serializable save(Recommend recommend);
	
	public List<Recommend> getRecommendList();
	
	public void delete();
}
