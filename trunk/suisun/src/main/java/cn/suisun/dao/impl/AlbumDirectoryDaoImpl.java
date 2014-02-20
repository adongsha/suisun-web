package cn.suisun.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.suisun.beans.AlbumDirectory;
import cn.suisun.beans.AlbumPic;
import cn.suisun.dao.AlbumDirectoryDao;
import cn.suisun.utils.jdbc.BaseDaoImpl;

@SuppressWarnings("all")
@Repository("AlbumDirectoryDao")
public class AlbumDirectoryDaoImpl extends BaseDaoImpl<AlbumDirectory>
		implements AlbumDirectoryDao {
	
	public void deleteAll(String id) {
		String ids[] = id.split(",") ;
		List<AlbumPic> pics = new ArrayList<AlbumPic>() ;
		
		for(String temp : ids){
			AlbumPic pic = new AlbumPic() ;
			pic.setUuid(temp) ;
			pics.add(pic) ;
		}
		super.getHibernateTemplate().deleteAll(pics) ;
	}

	@Override
	public List<AlbumDirectory> getAlbumDirectoryByAlbumId(String albumId) {
		return getHibernateTemplate().find(
				"from AlbumDirectory where albumId = ?",
				new String[] { albumId });
	}
	
	// 根据ID获取画册目录
	public AlbumDirectory getDirectoryByAlbumId(String uuid,String albumId) {
		List<AlbumDirectory> list = getHibernateTemplate().find(
				"from AlbumDirectory where uuid = ? and albumId = ?",
				new String[] {uuid,albumId });
		if(list != null && !list.isEmpty()){
			return list.get(0) ;
		}
		return null ;
	}

	// 新增画册目录
	public Serializable save(AlbumDirectory directory) {
		return getHibernateTemplate().save(directory);
	}
	
	// 更新画册目录信息
	public void update(AlbumDirectory directory) {
		super.getHibernateTemplate().update(directory) ;
	}

	@Override
	public List<AlbumDirectory> getAll() {
		
		return getHibernateTemplate().find("from AlbumDirectory");
	}
	
	// 删除画册目录信息
	public void deleteById(String uuid) {
		AlbumDirectory directory = new AlbumDirectory() ;
		directory.setUuid(uuid) ;
		super.getHibernateTemplate().delete(directory) ;
	}
}
