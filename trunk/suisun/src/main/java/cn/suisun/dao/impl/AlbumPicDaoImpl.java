package cn.suisun.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.suisun.beans.AlbumPic;
import cn.suisun.dao.AlbumPicDao;
import cn.suisun.utils.jdbc.BaseDaoImpl;

@Repository
public class AlbumPicDaoImpl extends BaseDaoImpl<AlbumPic> implements AlbumPicDao{

	@Override
	public List<AlbumPic> getAlbumPicListByADId(String albumDirectroyId) {
		String hql = "from AlbumPic where albumDirectoryId=?";
		
		return getHibernateTemplate().find(hql, new String[]{albumDirectroyId});
	}

	
}
