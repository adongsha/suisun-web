package cn.suisun.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.suisun.beans.AlbumUpdate;
import cn.suisun.dao.AlbumUpdateDao;
import cn.suisun.service.AlbumUpdateService;

@Service("AlbumUpdateService")
public class AlbumUpdateServiceImpl implements AlbumUpdateService{

	@Resource
	AlbumUpdateDao albumUpdateDao;
	
	@Override
	public void update(AlbumUpdate albumUpdate) {
		albumUpdateDao.update(albumUpdate);
	}

	@Override
	public Serializable save(AlbumUpdate albumUpdate) {
		return albumUpdateDao.save(albumUpdate);
	}

	@Override
	public AlbumUpdate getAlbumUpdateByAlbumId(String albumId) {
		// TODO Auto-generated method stub
		return albumUpdateDao.getAlbumUpdateByAlbumId(albumId);
	}

	
}
