package cn.suisun.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.suisun.beans.User;
import cn.suisun.dao.UserDao;
import cn.suisun.service.UserService;
import cn.suisun.vos.AlbumUpdateVO;

@Service("UserService")
public class UserServiceImpl implements UserService {

	@Resource
	UserDao userDao;

	public User Login(User user) {
		if (user != null) {
			return userDao.getUserByAccountAndPwd(user.getAccount(),
					user.getPassword());
		}
		return null;
	}

	public List<User> getPageUser(int currentPage, int pageSize, String power,
			String search) {
		if (!StringUtils.isEmpty(power)) {
			int p = Integer.parseInt(power);
			return userDao.getAllUserList(currentPage, pageSize, p, search);
		}
		return null;
	}

	public int getAountUser(String power, String search) {
		return userDao.getAllUserList(Integer.parseInt(power), search).size();
	}

	public void deleteUser(User user) {
		userDao.delete(user);
	}

	public User getUserByUid(String uuid) {
		return userDao.getUserByUid(uuid);
	}

	public Serializable add(User user) {
		return userDao.save(user);
	}

	public void update(User user) {
		userDao.update(user);
	}

	public User getUserByAccount(String account) {
		return userDao.getUserByAccount(account);
	}

	@Override
	public User getUserByAlbumId(String albumId) {
		return userDao.getUserByAlbumId(albumId);
	}

	// 获取所有企业名称
    public List<String> getAllEnterprise(){
    	return this.userDao.getAllEnterprise() ;
    }
    
    // 获取画册审批信息
    public List<AlbumUpdateVO> getAlbumUpdateInfo(int audit,String albumName) {
    	return this.userDao.getAlbumUpdateInfo(audit, albumName) ;
    }
    
    // 修改审核状态
    public void changeStatus(String uuid,int audit) {
    	this.userDao.changeStatus(uuid, audit) ;
    }

	@Override
	public List<User> getUserByIndustryId(String industryId, int currentPage,
			int pageSize) {
		return userDao.getUserByIndustryId(industryId, currentPage, pageSize);
	}

	@Override
	public int getUserByIndustryId(String industryId) {
		return userDao.getUserByIndustryId(industryId);
	}
    
    
}
