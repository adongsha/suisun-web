package cn.suisun.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.suisun.beans.AppUpdate;
import cn.suisun.dao.AppUpdateDao;
import cn.suisun.service.AppUpdateService;

@Service("AppUpdateService")
public class AppUpdateServiceImpl implements AppUpdateService{
	
	@Resource
	AppUpdateDao appUpdateDao ;

	@Override
	public List<AppUpdate> getAllApp() {
		return this.appUpdateDao.getAllApp() ;
	}

	@Override
	public void save(AppUpdate app) {
		this.appUpdateDao.save(app) ;
	}

	@Override
	public void update(AppUpdate app) {
		this.appUpdateDao.update(app) ;
	}

	@Override
	public AppUpdate getAppById(String uuid) {
		return this.appUpdateDao.getAppById(uuid) ;
	}
	
	public void deleteApp(String uuid){
		this.appUpdateDao.deleteApp(uuid) ;
	}

	@Override
	public AppUpdate getAppUpdateByPlatform(String platform) {
		return appUpdateDao.getAppUpdateByPlatform(platform);
	}
	
	
}
