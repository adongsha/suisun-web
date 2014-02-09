package cn.suisun.dao;

import java.util.List;

import cn.suisun.beans.AlbumDirectory;

public interface AlbumDirectoryDao {

	public List<AlbumDirectory> getAlbumDirectoryByAlbumId(String albumId);
}
