package cn.suisun.dao;

import java.io.Serializable;
import java.util.List;

import cn.suisun.beans.User;
import cn.suisun.utils.jdbc.BaseDao;
import cn.suisun.vos.AlbumUpdateVO;


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
    
    // 获取画册审批信息
    public List<AlbumUpdateVO> getAlbumUpdateInfo(int audit,String albumName) ;
    
    // 修改审核状态
    public void changeStatus(String uuid,int audit) ;
    
    public List<User> getUserByIndustryId(String industryId, int currentPage, int pageSize);

	public int getUserByIndustryId(String industryId);
}