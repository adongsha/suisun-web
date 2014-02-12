package cn.suisun.dao;

import java.util.List;

import cn.suisun.beans.AlbumPic;

public interface AlbumPicDao {

	/**
	 * 通过画册目录获取画册图片
	 * @param albumDirectroyId
	 * @return
	 */
	public List<AlbumPic> getAlbumPicListByADId(String albumDirectroyId);
	
	// 新增照片信息
	public void save(AlbumPic pic) ;
	
	// 修改照片信息
	public void update(AlbumPic pic) ;
	
	// 根据ID获取照片信息
	public AlbumPic getPicById(String uuid) ;
	
	// 根据ID删除照片
	public void deleteById(String uuid) ;
}
