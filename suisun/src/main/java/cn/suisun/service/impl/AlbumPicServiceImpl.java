package cn.suisun.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.suisun.beans.AlbumDirectory;
import cn.suisun.beans.AlbumPic;
import cn.suisun.dao.AlbumPicDao;
import cn.suisun.service.AlbumDirectoryService;
import cn.suisun.service.AlbumPicService;

@Service("AlbumPicService")
public class AlbumPicServiceImpl implements AlbumPicService{

	@Resource
	AlbumPicDao albumPicDao;

	@Override
	public List<AlbumPic> getAlbumPicListByADId(String albumDirectroyId) {
		return albumPicDao.getAlbumPicListByADId(albumDirectroyId);
	}
	


	
}
