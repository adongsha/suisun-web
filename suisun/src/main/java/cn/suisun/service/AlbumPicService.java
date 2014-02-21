package cn.suisun.service;

import java.util.List;

import cn.suisun.beans.AlbumPic;

public interface AlbumPicService {

	public List<AlbumPic> getAlbumPicListByADId(String albumDirectroyId);

	// 新增照片信息
	public void save(AlbumPic pic);
	
	// 修改照片信息
	public void update(AlbumPic pic) ;
	
	// 根据ID获取照片信息
	public AlbumPic getPicById(String uuid) ;
	
	// 根据ID删除照片
	public void deleteById(String uuid) ;
	
	// 照片上移
	public void shiftUpPic(String uuid,String up_uuid) ;
	
	public void shiftDownPic(String uuid,String down_uuid) ;
}
