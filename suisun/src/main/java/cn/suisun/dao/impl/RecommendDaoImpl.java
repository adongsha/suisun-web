package cn.suisun.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.suisun.beans.Recommend;
import cn.suisun.dao.RecommendDao;
import cn.suisun.utils.jdbc.BaseDaoImpl;

@Repository("RecommendDao")
public class RecommendDaoImpl extends BaseDaoImpl<Recommend> implements RecommendDao{

	@Override
	public List<Recommend> getRecommendPage(int currentPage, int pageSize) {
		
		return getPage("from Recommend where 1=1 order by sort", currentPage, pageSize);
	}

	@Override
	public int getRecommendPageAmount() {
		return getHibernateTemplate().find("from Recommend").size();
	}

	@Override
	public Serializable save(Recommend recommend) {
		return getHibernateTemplate().save(recommend);
	}
	
	

	
}
