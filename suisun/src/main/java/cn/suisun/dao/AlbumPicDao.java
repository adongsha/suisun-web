package cn.suisun.dao;

import java.io.Serializable;
import java.util.List;

import cn.suisun.beans.AlbumPic;

public interface AlbumPicDao {

	/**
	 * 通过画册目录获取画册图片
	 * @param albumDirectroyId
	 * @return
	 */
	public List<AlbumPic> getAlbumPicListByADId(String albumDirectroyId);
	
	public Serializable save(AlbumPic albumPic);
}
