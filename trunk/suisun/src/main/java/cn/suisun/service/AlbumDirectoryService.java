package cn.suisun.service;

import java.util.List;

import cn.suisun.beans.AlbumDirectory;

public interface AlbumDirectoryService {

	public List<AlbumDirectory> getAlbumDirectoryByAlbumId(String albumId);
}
