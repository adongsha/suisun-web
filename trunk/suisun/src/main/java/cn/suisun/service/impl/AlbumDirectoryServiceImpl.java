package cn.suisun.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.suisun.beans.Album;
import cn.suisun.beans.AlbumDirectory;
import cn.suisun.dao.AlbumDirectoryDao;
import cn.suisun.service.AlbumDirectoryService;

@Service("AlbumDirectoryService")
public class AlbumDirectoryServiceImpl implements AlbumDirectoryService{

	@Resource
	AlbumDirectoryDao albumDirectoryDao;
	
	@Override
	public List<AlbumDirectory> getAlbumDirectoryByAlbumId(String albumId) {
		return albumDirectoryDao.getAlbumDirectoryByAlbumId(albumId);
	}

	

	
}
