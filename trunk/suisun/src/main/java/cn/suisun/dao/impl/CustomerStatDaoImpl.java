package cn.suisun.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import cn.suisun.beans.CustomerStat;
import cn.suisun.dao.CustomerStatDao;
import cn.suisun.utils.jdbc.BaseDaoImpl;

@Repository("CustomerStatDao")
public class CustomerStatDaoImpl extends BaseDaoImpl<CustomerStat> implements CustomerStatDao{

	
	@Override
	public Serializable save(CustomerStat customerStat) {
		return getHibernateTemplate().save(customerStat);
	}

	@Override
	public List<CustomerStat> getCustomerStatByPage(int currentPage,
			int pageSize, String albumType, String album, int orderBy) {
		StringBuffer hql = new StringBuffer("from CustomerStat where 1=1 ");
		if(!StringUtils.isEmpty(albumType)){
			hql.append("and albumType='").append(albumType).append("' ");
		}
		if(!StringUtils.isEmpty(album)){
			hql.append("and albumLook='").append(album).append("'");
		}
		
		switch (orderBy) {
		case 3:
			hql.append("order by openNum");
			break;
		case 1:
			hql.append("order by downloadTime");
			break;
		case 2:
			hql.append("order by lastTimeLook");
			break;
		default:
			break;
		}
		return getPage(hql.toString(), currentPage, pageSize);
	}

	@Override
	public int getCustomerStatAmount(String albumType, String album, int orderBy) {
		StringBuffer hql = new StringBuffer("from CustomerStat where 1=1 ");
		if(!StringUtils.isEmpty(albumType)){
			hql.append("and albumType='").append(albumType).append("' ");
		}
		if(!StringUtils.isEmpty(album)){
			hql.append("and albumLook='").append(album).append("' ");
		}
		
		switch (orderBy) {
		case 1:
			hql.append(" order by openNum");
			break;
		case 2:
			hql.append(" order by downloadTime");
			break;
		case 3:
			hql.append(" order by lastTimeLook");
			break;
		default:
			break;
		}
		return getHibernateTemplate().find(hql.toString()).size();
	}

	@Override
	public List<CustomerStat> getCustomerStatByUserId(int currentPage,
			int pageSize, String id, String albumType, String album,int orderBy) {
		
		StringBuffer hql = new StringBuffer("from CustomerStat where 1=1 and userId = '")
		.append(id).append("' ");
		if(!StringUtils.isEmpty(albumType)){
			hql.append("and albumType='").append(albumType).append("' ");
		}
		if(!StringUtils.isEmpty(album)){
			hql.append("and albumLook='").append(album).append("'");
		}
		
		switch (orderBy) {
		case 3:
			hql.append("order by openNum");
			break;
		case 1:
			hql.append("order by downloadTime");
			break;
		case 2:
			hql.append("order by lastTimeLook");
			break;
		default:
			break;
		}
		return getPage(hql.toString(), currentPage, pageSize);
	}

	@Override
	public int getCustomerStatByIdAmount(String id, String albumType, String album,int orderBy) {
		StringBuffer hql = new StringBuffer("from CustomerStat where 1=1 and userId = '")
		.append(id).append("' ");
		if(!StringUtils.isEmpty(albumType)){
			hql.append("and albumType='").append(albumType).append("' ");
		}
		if(!StringUtils.isEmpty(album)){
			hql.append("and albumLook='").append(album).append("' ");
		}
		
		switch (orderBy) {
		case 1:
			hql.append(" order by openNum");
			break;
		case 2:
			hql.append(" order by downloadTime");
			break;
		case 3:
			hql.append(" order by lastTimeLook");
			break;
		default:
			break;
		}
		return getHibernateTemplate().find(hql.toString()).size();
	}

	
}
