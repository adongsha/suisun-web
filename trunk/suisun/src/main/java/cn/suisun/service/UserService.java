package cn.suisun.service;

import java.io.Serializable;
import java.util.List;

import cn.suisun.beans.User;
import cn.suisun.vos.AlbumUpdateVO;

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
	
	public List<User> getUserByIndustryId(String industryId,int currentPage, int pageSize);
	public int getUserByIndustryId(String industryId);
	
	// 获取所有企业名称
    public List<String> getAllEnterprise() ;
    
    // 获取画册审批信息
    public List<AlbumUpdateVO> getAlbumUpdateInfo(int audit,String albumName) ;
    
    // 修改审核状态
    public void changeStatus(String uuid,int audit) ;
}
