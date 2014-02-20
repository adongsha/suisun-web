package cn.suisun.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.suisun.beans.AlbumPic;
import cn.suisun.dao.AlbumPicDao;
import cn.suisun.utils.jdbc.BaseDaoImpl;

@Repository("AlbumPicDao")
public class AlbumPicDaoImpl extends BaseDaoImpl<AlbumPic> implements
		AlbumPicDao {

	@Override
	public List<AlbumPic> getAlbumPicListByADId(String albumDirectroyId) {
		String hql = "from AlbumPic where albumDirectoryId=?";

		return getHibernateTemplate().find(hql,
				new String[] { albumDirectroyId });
	}

	// 新增照片信息
	public void save(AlbumPic pic) {
		super.getHibernateTemplate().save(pic);
	}

	
	// 修改照片信息
	public void update(AlbumPic pic) {
		super.getHibernateTemplate().update(pic) ;
	}
	
	// 根据ID获取照片信息
	public AlbumPic getPicById(String uuid) {
		List<AlbumPic> list = super.getHibernateTemplate().find("from AlbumPic where uuid=?",new String[]{uuid}) ;
		
		if(list != null && !list.isEmpty()){
			return list.get(0) ;
		}
		return null ;
	}
	
	// 根据ID删除照片
	public void deleteById(String uuid) {
		AlbumPic pic = new AlbumPic() ;
		pic.setUuid(uuid) ;
		super.getHibernateTemplate().delete(pic) ;
	}

	@Override
	public List<AlbumPic> getAllPic() {
		return getHibernateTemplate().find("from AlbumPic");
	}
	
	
}
