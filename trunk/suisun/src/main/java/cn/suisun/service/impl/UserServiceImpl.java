package cn.suisun.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.suisun.beans.User;
import cn.suisun.dao.UserDao;
import cn.suisun.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	UserDao userDao;

	@Override
	public User Login(User user) {
		if(user != null){
			return userDao.getUserByAccountAndPwd(user.getAccount(),user.getPassword());			
		}
		return null;
	}

	@Override
	public List<User> getPageUser(int currentPage, int pageSize, String power, String search) {
		if(!StringUtils.isEmpty(power)){
			int p = Integer.parseInt(power);
			return userDao.getAllUserList(currentPage, pageSize, p, search);
		}
		return null;
	}

	@Override
	public int getAountUser(String power,String search) {
		return userDao.getAllUserList(Integer.parseInt(power),search).size();
	}

	@Override
	public void deleteUser(User user) {
		userDao.delete(user);
	}

	@Override
	public User getUserByUid(String uuid) {
		return userDao.getUserByUid(uuid);
	}

	@Override
	public Serializable add(User user) {
		return userDao.save(user);
	}

	@Override
	public void update(User user) {
		 userDao.update(user);
	}

	@Override
	public User getUserByAccount(String account) {
		return userDao.getUserByAccount(account);
	}

	
	
}