package cn.suisun.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.suisun.beans.AlbumUpdate;
import cn.suisun.dao.AlbumUpdateDao;
import cn.suisun.utils.jdbc.BaseDaoImpl;

@Repository("AlbumUpdateDao")
public class AlbumUpdateDaoImpl extends BaseDaoImpl<AlbumUpdate> implements
		AlbumUpdateDao {

	@Override
	public void update(AlbumUpdate albumUpdate) {
		getHibernateTemplate().update(albumUpdate);
	}

	@Override
	public Serializable save(AlbumUpdate albumUpdate) {
		return getHibernateTemplate().save(albumUpdate);
	}

	@Override
	public AlbumUpdate getAlbumUpdateByAlbumId(String albumId) {
		List<AlbumUpdate> list = getHibernateTemplate().find(
				"from AlbumUpdate where audit = 1 and albumId=?", new String[] { albumId });
		
		return list.size() > 0 ? list.get(0): null;
	}

}
