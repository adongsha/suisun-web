package cn.suisun.service;

import java.io.Serializable;
import java.util.List;

import cn.suisun.beans.Album;

public interface AlbumService {

	// 新增画册
	public Serializable Save(Album album);

	// 获取画册信息
	public List<Album> getAlbumList();

	// 根据用户ID获取画册信息
	public List<Album> getAlbumListByUserId(String userId);
	
	public Album getAlbumById(String id);
	
	// 根据搜索条件获取画册信息
	public List<Album> getAlbumList(String albumName,String enterpriseName) ;
	
	public Album getAlbumByAlias(String alias);
	
	public List<Album> getAlbumByKeyword(String keyword, int currentPage,
			int pageSize);
	
	public int getAlbumByKeywordAmount(String keyword);
	
	public List<Album> getAlbumListByIndustryId(String industryId,int currentPage, int pageSize);
	
	public int getAlbumListByIndustryIdAmouint(String industryId);
}
