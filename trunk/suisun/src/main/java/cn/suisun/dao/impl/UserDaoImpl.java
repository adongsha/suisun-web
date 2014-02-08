package cn.suisun.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import cn.suisun.beans.User;
import cn.suisun.dao.UserDao;
import cn.suisun.utils.jdbc.BaseDaoImpl;

/**
 * 描述： 用户模块DAO操作层
 * 
 * @author wanghj
 * 
 */
@Repository("UserDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public User getUserByAccountAndPwd(String account, String pwd) {
		String hql = "from User where account = ? and password = ?";
		List<User> list = getHibernateTemplate().find(hql,
				new String[] { account, pwd });
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public Serializable save(User user) {
		return getHibernateTemplate().save(user);
	}

	@Override
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

	@Override
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

	@Override
	public void delete(User user) {
		getHibernateTemplate().delete(user);
	}

	@Override
	public User getUserByUid(String uuId) {
		StringBuffer hql = new StringBuffer("from User where uuid = '").append(
				uuId).append("'");
		List<User> list = getHibernateTemplate().find(hql.toString());
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public void update(User user) {
		getHibernateTemplate().update(user);
	}

	@Override
	public User getUserByAccount(String account) {
		String hql = "from User where account = ?";
		List<User> list = getHibernateTemplate().find(hql,
				new String[] { account });
		return list.size() > 0 ? list.get(0) : null;
	}

}