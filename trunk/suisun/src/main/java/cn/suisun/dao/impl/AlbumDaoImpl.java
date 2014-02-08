package cn.suisun.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.suisun.beans.Album;
import cn.suisun.dao.AlbumDao;
import cn.suisun.utils.jdbc.BaseDaoImpl;

@Repository("AlbumDao")
public class AlbumDaoImpl extends BaseDaoImpl<Album> implements AlbumDao{

	@Override
	public Serializable save(Album album) {
		
		return getHibernateTemplate().save(album);
	}

	@Override
	public List<Album> getAlbumList() {
		String hql = "from Album";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public List<Album> getAlbumListByUserId(String userId) {
		String hql = "from Album where userId = ?";
		return getHibernateTemplate().find(hql,new String[]{userId});
	}

	
}
