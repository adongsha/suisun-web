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
             album.setAlbumPassword(i+"");
             album.setCreateTime(new Date());
             album.setIsAutoDownload(false);
             album.setAlbumAlias(AlbumAlias.getAlbumAlist());
             album.setAlbumName("画册"+i);
             switch (i%5) {
			case 0:
				album.setAlbumCover("cover1.jpg");    
				break;
			case 1:
				album.setAlbumCover("cover2.jpg");    
				break;
			case 2:
				album.setAlbumCover("cover3.jpg");    
				break;
			case 3:
				album.setAlbumCover("cover4.jpg");    
				break;
			case 4:
				album.setAlbumCover("cover5.jpg");  
				break;
			default:
				album.setAlbumCover("cover6.jpg");
				break;
			}
         
             album.setUserId(list.get(i).getUuid());
             albumDao.save(album);
		}
	}
	
	@Test
	public void testList(){
		List list = albumDao.getAlbumByKeyword("0", 1, 10);
		for(int i=0; i<list.size(); i++){
			Object[] obj = (Object[]) list.get(i);
			System.out.println(((Album)obj[0]).getAlbumAlias());
			System.out.println(obj[1].toString());
		}
		
	}
}
