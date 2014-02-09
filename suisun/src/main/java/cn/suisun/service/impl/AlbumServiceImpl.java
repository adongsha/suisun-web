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
	
	// 新增画册
	public Serializable Save(Album album) {
		return albumDao.save(album);
	}

	// 获取画册信息
	public List<Album> getAlbumList() {
		return albumDao.getAlbumList();
	}

	// 根据用户ID获取画册信息
	public List<Album> getAlbumListByUserId(String userId) {
		return albumDao.getAlbumListByUserId(userId);
	}

	@Override
	public Album getAlbumById(String id) {
		return albumDao.getAlbumById(id);
	}

	
	// 根据搜索条件获取画册信息
	public List<Album> getAlbumList(String albumName,String enterpriseName) {
		return this.albumDao.getAlbumList(albumName, enterpriseName) ;
	}
}
