package cn.suisun.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.suisun.beans.AlbumDirectory;
import cn.suisun.dao.AlbumDirectoryDao;
import cn.suisun.utils.jdbc.BaseDaoImpl;

@Repository("AlbumDirectoryDao")
public class AlbumDirectoryDaoImpl extends BaseDaoImpl<AlbumDirectory> implements AlbumDirectoryDao{

	@Override
	public List<AlbumDirectory> getAlbumDirectoryByAlbumId(String albumId) {
		return getHibernateTemplate().find("from AlbumDirectory where albumId = ?", new String[]{albumId});
	}

	
}
