package cn.suisun.service;

import java.util.List;

import cn.suisun.beans.Recommend;

public interface RecommendService {
	public List<Recommend> getRecommendPage(int currentPage, int pageSize);
	public int getRecommendPageAmount();
}
