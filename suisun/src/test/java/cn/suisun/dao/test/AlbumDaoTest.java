package cn.suisun.dao.test;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import cn.suisun.beans.Album;
import cn.suisun.dao.AlbumDao;
import cn.suisun.utils.spring.SpringBeanFactory;

public class AlbumDaoTest {

	AlbumDao albumDao;

	@Before
	public void init() {
		albumDao = SpringBeanFactory.getBean(AlbumDao.class);
	}

	@Test
	public void testSave() {
		for (int i = 0; i < 11; i++) {
             Album album = new Album();
             album.setAlbumEnglish("albumEnglish");
             album.setAlbumName(i+"-album");
             album.setAlbumPassword(i+"");
             album.setCreateTime(new Date());
             album.setIsAutoDownload(false);
             album.setUserId("402881e743e4e0b40143e4e0b9280001");
             albumDao.save(album);
		}
	}
}
