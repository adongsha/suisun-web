package cn.suisun.service;

import java.io.Serializable;
import java.util.List;

import cn.suisun.beans.Album;

public interface AlbumService {

	public Serializable Save(Album album);

	public List<Album> getAlbumList();

	public List<Album> getAlbumListByUserId(String userId);
}
