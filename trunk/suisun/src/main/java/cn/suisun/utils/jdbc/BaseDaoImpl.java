package cn.suisun.utils.jdbc;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import cn.suisun.utils.reflect.GenericsUtil;

/**
 * <p>
 *  基类  方便后面的扩长,里面集合了（后面有时间的话会扩展,把模板类的方法暴露出来,并对一些模板的方法对象化操作） 
 *  <code>{@link JdbcTemplate}}</code> springjdbc模板
 *  <code>{@link HibernateTemplate}}</code>hibernateTemplate 模板
 *  调用该这些方法可以拿到相关的操作方法
 *  </p>
 *  
 * @author wanghj
 * @data   2013-06-07
 *
 * @param <T>
 */
public class BaseDaoImpl<T> implements BaseDao<T>{

	private Class<T> clazz;
	
	@Resource
	JdbcTemplate jdbcTemplate;
	
	@Resource
	HibernateTemplate hibernateTemplate;
	
	
	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		
		clazz = (Class<T>) GenericsUtil.getSuperClassGenricType(getClass());
	}


	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@SuppressWarnings("unchecked")
	public List getPage(final String hql, final int currentPage, final int pageSize){
		
		return getHibernateTemplate().executeFind(new HibernateCallback() {

			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(hql);
				query.setFirstResult((currentPage-1)*pageSize);
				query.setMaxResults(pageSize);
				return query.list();
			}
			
		});
	}
	
}
