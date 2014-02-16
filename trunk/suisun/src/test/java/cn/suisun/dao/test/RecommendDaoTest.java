package cn.suisun.dao.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.suisun.beans.Album;
import cn.suisun.beans.Recommend;
import cn.suisun.dao.AlbumDao;
import cn.suisun.dao.RecommendDao;
import cn.suisun.utils.spring.SpringBeanFactory;

public class RecommendDaoTest {

	AlbumDao albumDao;
	RecommendDao recommendDao;

	@Before
	public void init() {
		albumDao = SpringBeanFactory.getBean(AlbumDao.class);
		recommendDao = SpringBeanFactory.getBean(RecommendDao.class);
	}

	@Test
	public void testSave() {
		List<Album> list = albumDao.getAlbumList();
		int i = 1;
		for(Album a : list){
			Recommend r = new Recommend();
			r.setAlbumId(a.getUuid());
			r.setSort(i);
			i++;
			recommendDao.save(r); 			
		}
	}
}
