package cn.suisun.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.suisun.beans.AppUpdate;
import cn.suisun.dao.AppUpdateDao;
import cn.suisun.utils.jdbc.BaseDaoImpl;

@SuppressWarnings("all")
@Repository("AppUpdateDao")
public class AppUpdateDaoImpl extends BaseDaoImpl<AppUpdate> implements AppUpdateDao {

	@Override
	public List<AppUpdate> getAllApp() {
		String hql = "from AppUpdate " ;
		return super.getHibernateTemplate().find(hql);
	}

	@Override
	public void save(AppUpdate app) {
		super.getHibernateTemplate().save(app) ;
	}

	@Override
	public void update(AppUpdate app) {
		super.getHibernateTemplate().update(app) ;
	}

	@Override
	public AppUpdate getAppById(String uuid) {
		String hql = "from AppUpdate where uuid = ? " ;
		List<AppUpdate> list = super.getHibernateTemplate().find(hql,new String[]{uuid}) ;
		
		if(list != null && !list.isEmpty()){
			return list.get(0) ;
		}
		return null ;
	}
	
	public void deleteApp(String uuid) {
		AppUpdate app = new AppUpdate() ;
		app.setUuid(uuid) ;
		super.getHibernateTemplate().delete(app) ;
	}

}
