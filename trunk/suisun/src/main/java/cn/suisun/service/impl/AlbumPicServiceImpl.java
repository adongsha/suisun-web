package cn.suisun.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.suisun.beans.AlbumPic;
import cn.suisun.dao.AlbumPicDao;
import cn.suisun.service.AlbumPicService;

@Service("AlbumPicService")
public class AlbumPicServiceImpl implements AlbumPicService {

	@Resource
	AlbumPicDao albumPicDao;

	@Override
	public List<AlbumPic> getAlbumPicListByADId(String albumDirectroyId) {
		return albumPicDao.getAlbumPicListByADId(albumDirectroyId);
	}

	// 新增照片信息
	public void save(AlbumPic pic) {
		this.albumPicDao.save(pic);
	}
	
	// 修改照片信息
	public void update(AlbumPic pic) {
		this.albumPicDao.update(pic) ;
	}
	
	// 根据ID获取照片信息
	public AlbumPic getPicById(String uuid) {
		return this.albumPicDao.getPicById(uuid) ;
	}
	
	// 根据ID删除照片
	public void deleteById(String uuid) {
		this.albumPicDao.deleteById(uuid) ;
	} 
}
