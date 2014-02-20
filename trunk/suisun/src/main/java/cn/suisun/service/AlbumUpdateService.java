package cn.suisun.service;

import java.io.Serializable;

import cn.suisun.beans.AlbumUpdate;

public interface AlbumUpdateService {
	
	public void update(AlbumUpdate albumUpdate);

	public Serializable save(AlbumUpdate albumUpdate);

	public AlbumUpdate getAlbumUpdateByAlbumId(String albumId);
}
