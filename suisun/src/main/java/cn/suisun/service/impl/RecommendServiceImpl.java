package cn.suisun.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.suisun.beans.Recommend;
import cn.suisun.dao.RecommendDao;
import cn.suisun.service.RecommendService;

@Service("RecommendService")
public class RecommendServiceImpl implements RecommendService{

	@Resource
	RecommendDao recommendDao;
	
	@Override
	public List<Recommend> getRecommendPage(int currentPage, int pageSize) {
		return recommendDao.getRecommendPage(currentPage, pageSize);
	}

	@Override
	public int getRecommendPageAmount() {
		return recommendDao.getRecommendPageAmount();
	}

	@Override
	public Serializable save(Recommend recommend) {
		return recommendDao.save(recommend);
	}

	
}
