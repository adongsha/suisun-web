package cn.suisun.dao;

import java.io.Serializable;
import java.util.List;

import cn.suisun.beans.AlbumDirectory;

public interface AlbumDirectoryDao {

	public List<AlbumDirectory> getAlbumDirectoryByAlbumId(String albumId);
	
	// 新增画册目录信息
	public Serializable save(AlbumDirectory directory);
	
	// 更新画册目录信息
	public void update(AlbumDirectory directory) ;
	
	// 删除画册目录信息
	public void deleteById(String uuid) ;
	
	public void deleteAll(String id) ;
	
	// 根据ID获取画册目录
	public AlbumDirectory getDirectoryByAlbumId(String uuid,String albumId) ;
	
	public List<AlbumDirectory> getAll();
}
