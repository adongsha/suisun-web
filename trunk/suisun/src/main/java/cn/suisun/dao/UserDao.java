package cn.suisun.dao;

import java.io.Serializable;
import java.util.List;

import cn.suisun.beans.User;
import cn.suisun.utils.jdbc.BaseDao;


/**
 * 描述：用户模块dao接口
 * 
 * @author wanghj
 *
 */
public interface UserDao extends BaseDao<User>{

	
	public User getUserByAccountAndPwd(String account, String pwd);
	
	public Serializable save(User user);
	
	public void delete(User user);
	
	public List<User> getAllUserList(int currentPage, int pageSize, int power, String search);
	
	public List<User> getAllUserList(int power, String search);
	
	public User getUserByUid(String uuId);
	
	public void update(User user);
	
	public User getUserByAccount(String account);
	
    public User getUserByAlbumId(String albumId);
    
    // 获取所有企业名称
    public List<String> getAllEnterprise() ;
}