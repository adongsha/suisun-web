package cn.suisun.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.suisun.beans.Album;
import cn.suisun.dao.AlbumDao;
import cn.suisun.utils.jdbc.BaseDaoImpl;

@Repository("AlbumDao")
public class AlbumDaoImpl extends BaseDaoImpl<Album> implements AlbumDao {

	// 新增画册
	public Serializable save(Album album) {

		return getHibernateTemplate().save(album);
	}

	// 获取画册信息
	public List<Album> getAlbumList() {
		String hql = "from Album";
		return getHibernateTemplate().find(hql);
	}

	// 根据用户ID获取画册信息
	public List<Album> getAlbumListByUserId(String userId) {
		String hql = "from Album where userId = ?";
		return getHibernateTemplate().find(hql, new String[] { userId });
	}

	@Override
	public Album getAlbumById(String id) {
		List<Album> list = getHibernateTemplate().find(
				"from Album where uuid=?", new String[] { id });
		return list.size() > 0 ? list.get(0) : null;
	}

	// 根据查询条件获取画册信息
	public List<Album> getAlbumList(String albumName, String enterpriseName) {
		String hql = "select a from Album a,User u where a.userId = u.uuid ";
		StringBuffer condition = new StringBuffer();

		if (albumName != null && !"".equals(albumName)) {
			condition.append("and a.albumName like '%" + albumName + "%' ");
		}

		if (enterpriseName != null && !"".equals(enterpriseName)) {
			condition
					.append("and u.enterpriseName = '" + enterpriseName + "' ");
		}
		// SQL拼接
		hql += condition;
		return super.getHibernateTemplate().find(hql);
	}

	@Override
	public Album getAlbumByAlias(String alias) {
		List<Album> list = getHibernateTemplate().find(
				"from Album where albumAlias=?", new String[] { alias });

		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public List<Album> getAlbumByKeyword(String keyword, int currentPage,
			int pageSize) {
		StringBuffer hql = new StringBuffer(
				"from Album a left User u join a.userId = u.uuid");
		hql.append("where a.albumName = '").append(keyword)
				.append("' or u.enterpriseName='").append(keyword).append("'");
		return getPage(hql.toString(), currentPage, pageSize);
	}

	@Override
	public int getAlbumByKeywordAmount(String keyword) {
		StringBuffer hql = new StringBuffer(
				"from Album a left User u join a.userId = u.uuid");
		hql.append("where a.albumName = '").append(keyword)
				.append("' or u.enterpriseName='").append(keyword).append("'");
		return getHibernateTemplate().find(hql.toString()).size();
	}

	@Override
	public List<Album> getAlbumListByIndustryId(String industryId,int currentPage, int pageSize){
		StringBuffer hql  = new StringBuffer("from Album");
		hql.append(" where industryId='").append(industryId).append("'");
		
		return getPage(hql.toString(), currentPage, pageSize);
	}

	@Override
	public int getAlbumListByIndustryIdAmouint(String industryId) {
		StringBuffer hql  = new StringBuffer("from Album");
		hql.append(" where industryId='").append(industryId).append("'");
		return getHibernateTemplate().find(hql.toString()).size();
	}

	
	
}
