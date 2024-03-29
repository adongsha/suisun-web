package cn.suisun.dao;

import java.io.Serializable;
import java.util.List;

import cn.suisun.beans.Album;
import cn.suisun.beans.AlbumUpdate;

// 画册管理接口
public interface AlbumDao {

	// 新增画册
	public Serializable save(Album album);
	
	// 修改画册
	public void update(Album album);

	// 获取所有画册信息
	public List<Album> getAlbumList();

	// 根据用户ID获取画册信息
	public List<Album> getAlbumListByUserId(String userId);

	public Album getAlbumById(String id);
	

	// 根据查询条件获取画册信息
	public List<Album> getAlbumList(String albumName, String enterpriseName);

	public Album getAlbumByAlias(String alias);

	public List getAlbumByKeyword(String key, int currentPage,
			int pageSize);

	public int getAlbumByKeywordAmount(String key);
	
	public List<Album> getAlbumListByIndustryId(String industryId,int currentPage, int pageSize);
	public int getAlbumListByIndustryIdAmouint(String industryId);
	
	// 保存发布信息
	public void savePublish(AlbumUpdate publish) ;
	
	public void deleteAlbum(String uuid) ;
}
