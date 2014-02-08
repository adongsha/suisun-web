package cn.suisun.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.suisun.beans.Album;
import cn.suisun.dao.AlbumDao;
import cn.suisun.service.AlbumService;

@Service("AlbumService")
public class AlbumServiceImpl implements AlbumService{

	@Resource
	AlbumDao albumDao;
	
	@Override
	public Serializable Save(Album album) {
		return albumDao.save(album);
	}

	@Override
	public List<Album> getAlbumList() {
		return albumDao.getAlbumList();
	}

	@Override
	public List<Album> getAlbumListByUserId(String userId) {
		
		return albumDao.getAlbumListByUserId(userId);
	}

	
}
