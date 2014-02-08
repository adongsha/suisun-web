package cn.suisun.dao;

import java.io.Serializable;
import java.util.List;

import cn.suisun.beans.Album;

public interface AlbumDao {

	public Serializable save(Album album);

	public List<Album> getAlbumList();

	public List<Album> getAlbumListByUserId(String userId);

}
