package cn.suisun.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import cn.suisun.beans.AlbumUpdate;
import cn.suisun.beans.User;
import cn.suisun.dao.UserDao;
import cn.suisun.utils.jdbc.BaseDaoImpl;
import cn.suisun.vos.AlbumUpdateVO;

/**
 * 描述： 用户模块DAO操作层
 * 
 * @author wanghj
 * 
 */
@SuppressWarnings("all")
@Repository("UserDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	public User getUserByAccountAndPwd(String account, String pwd) {
		String hql = "from User where account = ? and password = ?";
		List<User> list = getHibernateTemplate().find(hql,
				new String[] { account, pwd });
		return list.size() > 0 ? list.get(0) : null;
	}

	public Serializable save(User user) {
		return getHibernateTemplate().save(user);
	}

	public List<User> getAllUserList(int currentPage, int pageSize, int power,
			String search) {
		StringBuffer hql = new StringBuffer("from User where 1 = 1 ");
		if (power != 3) {
			hql.append(" and power=").append(power);
		}
		if (!StringUtils.isEmpty(search)) {
			hql.append(" and (account like '%").append(search).append("%'");
			hql.append(" or enterpriseName like '%").append(search)
					.append("%')");
		}
		return getPage(hql.toString(), currentPage, pageSize);
	}

	public List<User> getAllUserList(int power, String search) {
		StringBuffer hql = new StringBuffer("from User where 1 = 1");
		if (power != 3) {
			hql.append(" and power=").append(power);
		}
		if (!StringUtils.isEmpty(search)) {
			hql.append(" and (account like '%").append(search).append("%'");
			hql.append(" or enterpriseName like '%").append(search)
					.append("%')");
		}
		return getHibernateTemplate().find(hql.toString());
	}

	public void delete(User user) {
		getHibernateTemplate().delete(user);
	}

	public User getUserByUid(String uuId) {
		StringBuffer hql = new StringBuffer("from User where uuid = '").append(
				uuId).append("'");
		List<User> list = getHibernateTemplate().find(hql.toString());
		return list.size() > 0 ? list.get(0) : null;
	}

	public void update(User user) {
		getHibernateTemplate().update(user);
	}

	public User getUserByAccount(String account) {
		String hql = "from User where account = ?";
		List<User> list = getHibernateTemplate().find(hql,
				new String[] { account });
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public User getUserByAlbumId(String userId) {
		String hql = "from User where uuid=?";
		List<User> list = getHibernateTemplate().find(hql, new String[]{userId});
		return list.size() > 0 ? list.get(0) : null; 
	}
	
	// 获取所有企业名称
    public List<String> getAllEnterprise() {
    	String hql = "from User" ;
    	// 获取用户信息
    	List<User> users = super.getHibernateTemplate().find(hql,new String[]{}) ;
    	// 企业名称
    	List<String> result = new ArrayList<String>() ;
    	
    	for(User user : users){
    		// 添加企业名称
    		result.add(user.getEnterpriseName()) ;
    	}
    	return result ;
    }

    // 获取画册审批信息
    public List<AlbumUpdateVO> getAlbumUpdateInfo(int audit,String albumName) {
    	List<AlbumUpdateVO> result = new ArrayList<AlbumUpdateVO>() ;
    	String hql = "select u.uuid,s.enterpriseName,a.albumName,a.albumEnglish,u.createTime,u.updateContent,u.audit "
    				+ "from AlbumUpdate u,Album a,User s "
    				+ "where u.albumId = a.uuid and u.userId = s.uuid " ;
    	
    	if(albumName != null && !"".equals(albumName)){
    		hql += " and a.albumName like '%"+ albumName +"'%" ;
    	}
    	
    	hql += " order by u.audit" ;
    	
    	List<Object[]> objs = super.getHibernateTemplate().find(hql) ;
    	AlbumUpdateVO entity = null ;

    	for(Object[] obj : objs){
    		entity = new AlbumUpdateVO() ;
    		entity.setUuid(String.valueOf(obj[0])) ;
    		entity.setUserName(String.valueOf(obj[1])) ;
    		entity.setAlbumName(String.valueOf(obj[2])) ;
    		entity.setEnglisthName(String.valueOf(obj[3])) ;
			entity.setCreateTime(String.valueOf(obj[4])) ;
    		entity.setUpdateContent(String.valueOf(obj[5])) ;
    		entity.setAudit(Integer.parseInt(String.valueOf(obj[6]))) ;
    		result.add(entity) ;
    	}
    	return result ;
    }
    
    // 修改审核状态
    public void changeStatus(String uuid,int audit) {
    	List<AlbumUpdate> list = super.getHibernateTemplate().find("from AlbumUpdate where uuid = ?",new String[]{uuid}) ;
    	AlbumUpdate entity = new AlbumUpdate() ;
    	
    	if(list != null && !list.isEmpty()){
    		entity = list.get(0) ;
    	}
    	entity.setCreateTime(new Date()) ;
    	// 修改审批状态
    	entity.setAudit(audit) ;
    	// 修改审核信息
    	super.getHibernateTemplate().update(entity) ;
     }

	@Override
	public List<User> getUserByIndustryId(String industryId, int currentPage,
			int pageSize) {
		StringBuffer hql = new StringBuffer("from User where industryId ='").append(industryId).append("'");
		return getPage(hql.toString(), currentPage, pageSize);
	}
    
	@Override
	public int getUserByIndustryId(String industryId) {
		List<User> list = getHibernateTemplate().find("from User where industryId=?", new String[]{industryId});
		return list.size();
	}
}