package cn.suisun.dao;

import java.io.Serializable;

import cn.suisun.beans.AlbumUpdate;

public interface AlbumUpdateDao {

	public void update(AlbumUpdate albumUpdate);
	
	public Serializable save(AlbumUpdate albumUpdate);
	
	public AlbumUpdate getAlbumUpdateByAlbumId(String albumId);
}
