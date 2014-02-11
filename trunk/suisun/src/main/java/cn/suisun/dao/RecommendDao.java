package cn.suisun.dao;

import java.util.List;

import cn.suisun.beans.Recommend;

public interface RecommendDao {

	public List<Recommend> getRecommendPage(int currentPage, int pageSize);
	public int getRecommendPageAmount();
}
