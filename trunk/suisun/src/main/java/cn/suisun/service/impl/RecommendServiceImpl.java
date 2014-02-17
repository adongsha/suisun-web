package cn.suisun.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.suisun.beans.Album;
import cn.suisun.beans.Recommend;
import cn.suisun.dao.AlbumDao;
import cn.suisun.dao.RecommendDao;
import cn.suisun.service.RecommendService;
import cn.suisun.vos.RecommendVo;

@Service("RecommendService")
public class RecommendServiceImpl implements RecommendService{

	@Resource
	RecommendDao recommendDao;
	
	@Resource
	AlbumDao albumDao;
	
	@Override
	public List<Recommend> getRecommendPage(int currentPage, int pageSize) {
		return recommendDao.getRecommendPage(currentPage, pageSize);
	}

	@Override
	public int getRecommendPageAmount() {
		return recommendDao.getRecommendPageAmount();
	}

	@Override
	public Serializable save(Recommend recommend) {
		return recommendDao.save(recommend);
	}

	@Override
	public List<RecommendVo> getRecommendVoList() {
		List<Recommend> list = recommendDao.getRecommendList();
		List<RecommendVo> listVo = new ArrayList<RecommendVo>();
		for(Recommend r : list){
			Album album = albumDao.getAlbumById(r.getAlbumId());
		    RecommendVo rVo = new RecommendVo();
		    rVo.setAlbumId(album.getUuid());
		    rVo.setAlbumName(album.getAlbumName());
		    rVo.setRecommendId(r.getUuid());
		    rVo.setSort(r.getSort());
		    listVo.add(rVo);
		}
		return listVo;
	}

	
	@Override
	public List<Recommend> getRecommendList() {
		return recommendDao.getRecommendList();
	}

	@Override
	public boolean addRecommend(String rid) {
		boolean flag = true;
		if(!StringUtils.isEmpty(rid)){
			String[] s = rid.split(",");
			recommendDao.delete();
			for(int i=0; i<s.length; i++){
				Recommend recommend = new Recommend();
				recommend.setAlbumId(s[i]);
				recommend.setSort(i+1);
				if(StringUtils.isEmpty(recommendDao.save(recommend))){
					flag = false;
				}
			}
		}
		return flag;
	}

	
}
