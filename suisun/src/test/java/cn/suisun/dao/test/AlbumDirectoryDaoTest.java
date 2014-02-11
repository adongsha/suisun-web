package cn.suisun.dao.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.suisun.beans.Album;
import cn.suisun.beans.AlbumDirectory;
import cn.suisun.beans.AlbumPic;
import cn.suisun.dao.AlbumDao;
import cn.suisun.dao.AlbumDirectoryDao;
import cn.suisun.dao.AlbumPicDao;
import cn.suisun.dao.UserDao;
import cn.suisun.utils.spring.SpringBeanFactory;

public class AlbumDirectoryDaoTest {

	AlbumDao albumDao;
	UserDao userDao;
	AlbumDirectoryDao albumDirectoryDao;
	AlbumPicDao albumPicDao;

	@Before
	public void init() {
		albumDao = SpringBeanFactory.getBean(AlbumDao.class);
		userDao = SpringBeanFactory.getBean(UserDao.class);
		albumDirectoryDao = SpringBeanFactory.getBean(AlbumDirectoryDao.class);
		albumPicDao = SpringBeanFactory.getBean(AlbumPicDao.class);
	}

	@Test
	public void testSave() {
		List<Album> list = albumDao.getAlbumList();
		for (int i = 0; i < 10; i++) {
			AlbumDirectory ad = new AlbumDirectory();
			ad.setAlbumId(list.get(i).getUuid());
			ad.setDirectoryEnglish("directoryEnglish-" + i);
			ad.setDirectoryName("directoryName-" + i);
			ad.setIndexDirectory(1);
			ad.setPicNum(2);
			albumDirectoryDao.save(ad);
		}
	}

	@Test
	public void testAlbumPicSave() {
		List<AlbumDirectory> list = albumDirectoryDao.getAll();
		for (AlbumDirectory a : list) {
			for (int i = 0; i < 2; i++) {
                AlbumPic ap = new AlbumPic();
                ap.setAlbumDirectoryId(a.getUuid());
                ap.setAudit(1);
                ap.setEnglishName("englishName"+i);
                ap.setIndexPic(i);
                ap.setPicName("picName"+i);
                ap.setPicUrl("picUrl"+i);
                albumPicDao.save(ap);
			}
		}
	}
}
