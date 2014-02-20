package cn.suisun.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.suisun.beans.Album;
import cn.suisun.beans.AlbumUpdate;
import cn.suisun.dao.AlbumDao;
import cn.suisun.service.AlbumService;

@Service("AlbumService")
public class AlbumServiceImpl implements AlbumService {

	@Resource
	AlbumDao albumDao;

	// 新增画册
	public Serializable Save(Album album) {
		return albumDao.save(album);
	}

	// 修改画册
	public void update(Album album) {
		this.albumDao.update(album);
	}

	// 获取画册信息
	public List<Album> getAlbumList() {
		return albumDao.getAlbumList();
	}

	// 根据用户ID获取画册信息
	public List<Album> getAlbumListByUserId(String userId) {
		return albumDao.getAlbumListByUserId(userId);
	}

	@Override
	public Album getAlbumById(String id) {
		return albumDao.getAlbumById(id);
	}

	// 根据搜索条件获取画册信息
	public List<Album> getAlbumList(String albumName, String enterpriseName) {
		return this.albumDao.getAlbumList(albumName, enterpriseName);
	}

	// 保存发布信息
	public void savePublish(AlbumUpdate publish) {
		this.albumDao.savePublish(publish);
	}

	@Override
	public Album getAlbumByAlias(String alias) {
		return albumDao.getAlbumByAlias(alias);
	}

	@Override
	public List getAlbumByKeyword(String keyword, int currentPage, int pageSize) {
		return albumDao.getAlbumByKeyword(keyword, currentPage, pageSize);
	}

	@Override
	public int getAlbumByKeywordAmount(String keyword) {
		return albumDao.getAlbumByKeywordAmount(keyword);
	}

	@Override
	public List<Album> getAlbumListByIndustryId(String industryId,
			int currentPage, int pageSize) {
		return albumDao.getAlbumListByIndustryId(industryId, currentPage,
				pageSize);
	}

	@Override
	public int getAlbumListByIndustryIdAmouint(String industryId) {
		return albumDao.getAlbumListByIndustryIdAmouint(industryId);
	}

	public void deleteAlbum(String uuid) {
		this.albumDao.deleteAlbum(uuid) ;
	}

}
