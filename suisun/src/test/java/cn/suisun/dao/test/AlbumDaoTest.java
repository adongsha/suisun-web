package cn.suisun.dao.test;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.suisun.beans.Album;
import cn.suisun.beans.User;
import cn.suisun.dao.AlbumDao;
import cn.suisun.dao.UserDao;
import cn.suisun.utils.AlbumAlias;
import cn.suisun.utils.spring.SpringBeanFactory;

public class AlbumDaoTest {

	AlbumDao albumDao;
	UserDao userDao;

	@Before
	public void init() {
		albumDao = SpringBeanFactory.getBean(AlbumDao.class);
		userDao = SpringBeanFactory.getBean(UserDao.class);
	}

	@Test
	public void testSave() {
		List<User> list = userDao.getAllUserList(2, null);
		for (int i = 0; i < 10; i++) {
             Album album = new Album();
             album.setAlbumEnglish("albumEnglish");
             album.setAlbumName(i+"-album");
             album.setAlbumPassword(i+"");
             album.setCreateTime(new Date());
             album.setIsAutoDownload(false);
             album.setAlbumAlias(AlbumAlias.getAlbumAlist());
             if(i%2==0){
            	 album.setAlbumCover("cover1.jpg");            	 
             } else {
            	 album.setAlbumCover("cover2.jpg");
             }
             album.setUserId(list.get(i).getUuid());
             albumDao.save(album);
		}
	}
}
