package cn.suisun.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import cn.suisun.beans.Recommend;
import cn.suisun.dao.RecommendDao;
import cn.suisun.utils.jdbc.BaseDaoImpl;

@Repository("RecommendDao")
public class RecommendDaoImpl extends BaseDaoImpl<Recommend> implements RecommendDao{

	@Override
	public List<Recommend> getRecommendPage(int currentPage, int pageSize) {
		
		return getPage("from Recommend where 1=1 order by sort asc", currentPage, pageSize);
	}

	@Override
	public int getRecommendPageAmount() {
		return getHibernateTemplate().find("from Recommend").size();
	}

	@Override
	public Serializable save(Recommend recommend) {
		return getHibernateTemplate().save(recommend);
	}

	@Override
	public List<Recommend> getRecommendList() {
		return getHibernateTemplate().find("from Recommend order by sort asc");
	}

	@Override
	public void delete() {
		Session session=getHibernateTemplate().getSessionFactory().openSession();
	      String hql="delete Recommend";
	      Transaction t=null;
	      try
	      {
	       t=session.beginTransaction();
	       Query q=session.createQuery(hql);
	          q.executeUpdate();
	          t.commit();
	      }catch(Exception ex)
	      {
	       if(t!=null)
	       {
	        t.rollback();
	       }
	      }finally
	      {
	       session.close();
	      }
	}
	
	
	
}
