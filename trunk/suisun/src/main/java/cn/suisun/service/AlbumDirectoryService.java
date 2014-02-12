package cn.suisun.service;

import java.io.Serializable;
import java.util.List;

import cn.suisun.beans.AlbumDirectory;

public interface AlbumDirectoryService {

	public List<AlbumDirectory> getAlbumDirectoryByAlbumId(String albumId);
	
	// 新增画册目录
	public Serializable save(AlbumDirectory directory) ;
	
	// 更新画册目录信息
	public void update(AlbumDirectory directory) ;
	
	// 删除画册目录信息
	public void deleteById(String uuid) ;
	
	// 根据ID获取画册目录
	public AlbumDirectory getDirectoryByAlbumId(String uuid,String albumId) ;
}
