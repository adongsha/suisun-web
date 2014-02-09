package cn.suisun.dao;

import java.io.Serializable;
import java.util.List;

import cn.suisun.beans.Album;

// 画册管理接口
public interface AlbumDao {

	// 新增画册
	public Serializable save(Album album);

	// 获取所有画册信息
	public List<Album> getAlbumList();

	// 根据用户ID获取画册信息
	public List<Album> getAlbumListByUserId(String userId);

	// 根据查询条件获取画册信息
	public List<Album> getAlbumList(String albumName,String enterpriseName) ;
}
