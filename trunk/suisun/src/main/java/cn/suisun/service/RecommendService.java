package cn.suisun.service;

import java.io.Serializable;
import java.util.List;

import cn.suisun.beans.Recommend;
import cn.suisun.vos.RecommendVo;

public interface RecommendService {
	public List<Recommend> getRecommendPage(int currentPage, int pageSize);

	public int getRecommendPageAmount();
	
	public Serializable save(Recommend recommend);
	
	public List<RecommendVo> getRecommendVoList();
	
	public List<Recommend> getRecommendList();
	
	public boolean addRecommend(String rid);
}
