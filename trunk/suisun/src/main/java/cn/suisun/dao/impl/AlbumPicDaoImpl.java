package cn.suisun.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.suisun.beans.AlbumPic;
import cn.suisun.dao.AlbumPicDao;
import cn.suisun.utils.jdbc.BaseDaoImpl;

@SuppressWarnings("all")
@Repository("AlbumPicDao")
public class AlbumPicDaoImpl extends BaseDaoImpl<AlbumPic> implements
		AlbumPicDao {

	@Override
	public List<AlbumPic> getAlbumPicListByADId(String albumDirectroyId) {
		String hql = "from AlbumPic where albumDirectoryId=? order by indexPic";

		return getHibernateTemplate().find(hql,
				new String[] { albumDirectroyId });
	}

	// 新增照片信息
	public void save(AlbumPic pic) {
		super.getHibernateTemplate().save(pic);
	}

	// 修改照片信息
	public void update(AlbumPic pic) {
		super.getHibernateTemplate().update(pic);
	}

	// 根据ID获取照片信息
	public AlbumPic getPicById(String uuid) {
		List<AlbumPic> list = super.getHibernateTemplate().find(
				"from AlbumPic where uuid=?", new String[] { uuid });

		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	// 根据ID删除照片
	public void deleteById(String uuid) {
		AlbumPic pic = new AlbumPic();
		pic.setUuid(uuid);
		super.getHibernateTemplate().delete(pic);
	}

	@Override
	public List<AlbumPic> getAllPic() {
		return getHibernateTemplate().find("from AlbumPic");
	}

	// 照片上移
	public void shiftUpPic(String uuid,String up_uuid) {
		List<AlbumPic> picList = super.getHibernateTemplate().find("from AlbumPic where uuid in (?,?)",new String[]{uuid,up_uuid}) ;
		
		AlbumPic pic = null ;
		AlbumPic up_pic = null ;
		
		if(picList != null && !picList.isEmpty()){
			for(AlbumPic p : picList){
				if(uuid.equals(p.getUuid())){
					pic = p ;
				}else if(up_uuid.equals(p.getUuid())){
					up_pic = p ;
				}
			}
		}
		// 交换照片序号
		int index = pic.getIndexPic() ;
		pic.setIndexPic(up_pic.getIndexPic()) ;
		up_pic.setIndexPic(index) ;
		
		super.getHibernateTemplate().update(pic) ;
		super.getHibernateTemplate().update(up_pic) ;
	}
	
	public void shiftDownPic(String uuid,String down_uuid) {
		List<AlbumPic> picList = super.getHibernateTemplate().find("from AlbumPic where uuid in (?,?)",new String[]{uuid,down_uuid}) ;
		
		AlbumPic pic = null ;
		AlbumPic down_pic = null ;
		
		if(picList != null && !picList.isEmpty()){
			for(AlbumPic p : picList){
				if(uuid.equals(p.getUuid())){
					pic = p ;
				}else if(down_uuid.equals(p.getUuid())){
					down_pic = p ;
				}
			}
		}
		// 交换照片序号
		int index = pic.getIndexPic() ;
		pic.setIndexPic(down_pic.getIndexPic()) ;
		down_pic.setIndexPic(index) ;
		
		super.getHibernateTemplate().update(pic) ;
		super.getHibernateTemplate().update(down_pic) ;
	}
}
