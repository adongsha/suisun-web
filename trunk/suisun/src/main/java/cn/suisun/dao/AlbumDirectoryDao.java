package cn.suisun.dao;

import java.io.Serializable;
import java.util.List;

import cn.suisun.beans.AlbumDirectory;

public interface AlbumDirectoryDao {

	public List<AlbumDirectory> getAlbumDirectoryByAlbumId(String albumId);
	
	public Serializable save(AlbumDirectory albumDirectory);
	
	public List<AlbumDirectory> getAll();
}
