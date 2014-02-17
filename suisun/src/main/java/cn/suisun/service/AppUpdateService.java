package cn.suisun.service;

import java.util.List;

import cn.suisun.beans.AppUpdate;

public interface AppUpdateService {
	
	public List<AppUpdate> getAllApp();

	public void save(AppUpdate app);

	public void update(AppUpdate app);

	public AppUpdate getAppById(String uuid);
	
	public void deleteApp(String uuid) ;
}
