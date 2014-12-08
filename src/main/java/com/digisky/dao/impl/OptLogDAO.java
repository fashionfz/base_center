/**   
 * @Title: OptLogDAO.java 
 * @Package com.digisky.dao.impl 
 * @Description: TODO
 * @author dengbin
 * @date 2014年12月3日 上午11:41:53 
 * @version V1.0   
 */
package com.digisky.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.digisky.dao.IOptLogDAO;
import com.digisky.po.OperateLog;

/** 
 * @ClassName: OptLogDAO 
 * @Description: TODO
 * @author dengbin
 * @date 2014年12月3日 上午11:41:53  
 */
@Repository
public class OptLogDAO implements IOptLogDAO{

	@Autowired
	private SessionFactory sessionFactory;
	/* (non-Javadoc)
	 * @see com.digisky.dao.IOptLogDAO#save(com.digisky.po.OperateLog) 
	 */
	@Override
	public void save(OperateLog log) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(log);
	}
	/* (non-Javadoc)
	 * @see com.digisky.dao.IOptLogDAO#query() 
	 */
	@Override
	public List<OperateLog> query(String userName,String menuName,int page,int rows) {
		String hql="from OperateLog l where 1=1";
		if(null!=userName && !"".equals(userName)){
			hql+=" and l.userName like :userName";
		}
		if(null!=menuName && !"".equals(menuName)){
			hql+=" and l.menuName like :menuName";
		}
		hql+=" order by l.optTime desc";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		if(null!=userName && !"".equals(userName)){
			query.setParameter("userName", userName+"%");
		}
		if(null!=menuName && !"".equals(menuName)){
			query.setParameter("menuName", menuName+"%");
		}
		if(page>0&&rows>0){
            query.setFirstResult((page-1)*rows);
            query.setMaxResults(rows);
		}
		return query.list();
	}

}
