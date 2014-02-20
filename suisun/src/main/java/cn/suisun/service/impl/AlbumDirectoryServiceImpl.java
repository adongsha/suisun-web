package cn.suisun.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.suisun.beans.AlbumDirectory;
import cn.suisun.dao.AlbumDirectoryDao;
import cn.suisun.service.AlbumDirectoryService;

@Service("AlbumDirectoryService")
public class AlbumDirectoryServiceImpl implements AlbumDirectoryService {

	@Resource
	AlbumDirectoryDao albumDirectoryDao;

	@Override
	public List<AlbumDirectory> getAlbumDirectoryByAlbumId(String albumId) {
		return albumDirectoryDao.getAlbumDirectoryByAlbumId(albumId);
	}
	
	public void deleteAll(String id){
		this.albumDirectoryDao.deleteAll(id) ;
	}
	
	// 新增画册信息
	public Serializable save(AlbumDirectory directory) {
		return this.albumDirectoryDao.save(directory) ;
	}
	// 更新画册目录信息
	public void update(AlbumDirectory directory) {
		this.albumDirectoryDao.update(directory) ;
	}
	
	// 根据ID获取画册目录
	public AlbumDirectory getDirectoryByAlbumId(String uuid,String albumId) {
		return this.albumDirectoryDao.getDirectoryByAlbumId(uuid, albumId) ;
	}
	
	// 删除画册目录信息
	public void deleteById(String uuid) {
		this.albumDirectoryDao.deleteById(uuid) ;
	}
}
