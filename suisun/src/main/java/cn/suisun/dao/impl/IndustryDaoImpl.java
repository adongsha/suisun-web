package cn.suisun.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.suisun.beans.Industry;
import cn.suisun.dao.IndustryDao;
import cn.suisun.utils.jdbc.BaseDaoImpl;

@Repository("IndustryDao")
public class IndustryDaoImpl extends BaseDaoImpl<Industry> implements
		IndustryDao {

	@Override
	public Serializable save(Industry industry) {

		return getHibernateTemplate().save(industry);
	}

	@Override
	public List<Industry> getIndustryListByPage(int currentPage, int pageSize) {
		String hql = "from Industry";
		return getPage(hql, currentPage, pageSize);
	}

	@Override
	public int getIndustryListByPageAmount() {
		String hql = "from Industry";

		return getHibernateTemplate().find(hql).size();
	}

	@Override
	public void update(Industry industry) {
		getHibernateTemplate().update(industry);
	}

	@Override
	public void delete(Industry industry) {
		getHibernateTemplate().delete(industry);
	}

	@Override
	public List<Industry> getIndustry() {
		return getHibernateTemplate().find("from Industry");
	}

	@Override
	public Industry getIndustryById(String id) {
		List<Industry> list = getHibernateTemplate().find(
				"from Industry where uuid = ?", new String[] { id });

		return list.size() > 0 ? list.get(0) : null;
	}

}
