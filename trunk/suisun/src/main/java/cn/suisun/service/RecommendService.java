package cn.suisun.service;

import java.io.Serializable;
import java.util.List;

import cn.suisun.beans.Recommend;

public interface RecommendService {
	public List<Recommend> getRecommendPage(int currentPage, int pageSize);

	public int getRecommendPageAmount();
	
	public Serializable save(Recommend recommend);
}
