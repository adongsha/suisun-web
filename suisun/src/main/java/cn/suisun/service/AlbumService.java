package cn.suisun.service;

import java.io.Serializable;
import java.util.List;

import cn.suisun.beans.Album;
import cn.suisun.beans.AlbumUpdate;

public interface AlbumService {

	// 新增画册
	public Serializable Save(Album album);
	
	// 修改画册
	public void update(Album album) ;
	
	// 获取画册信息
	public List<Album> getAlbumList();

	// 根据用户ID获取画册信息
	public List<Album> getAlbumListByUserId(String userId);
	
	public Album getAlbumById(String id);
	
	// 根据搜索条件获取画册信息
	public List<Album> getAlbumList(String albumName,String enterpriseName) ;
	
	// 保存发布信息
	public void savePublish(AlbumUpdate publish) ;
	
	public Album getAlbumByAlias(String alias);
	
	public List getAlbumByKeyword(String keyword, int currentPage,
			int pageSize);
	
	public int getAlbumByKeywordAmount(String keyword);
	
	public List<Album> getAlbumListByIndustryId(String industryId,int currentPage, int pageSize);
	
	public int getAlbumListByIndustryIdAmouint(String industryId);
	
	public void deleteAlbum(String uuid) ;
}
