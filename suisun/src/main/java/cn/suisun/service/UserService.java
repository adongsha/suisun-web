package cn.suisun.service;

import java.io.Serializable;
import java.util.List;

import cn.suisun.beans.User;

public interface UserService {

	public User Login(User user);
	
	public List<User> getPageUser(int currentPage, int pageSize, String power, String search);
	
	public int getAountUser(String power, String search);
	
	public void deleteUser(User user);
	
	public User getUserByUid(String uuid);
	
	public Serializable add(User user);
	
	public void update(User user);
	
	public User getUserByAccount(String account);
	
	public User getUserByAlbumId(String albumId);
}
