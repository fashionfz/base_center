/**   
 * @Title: DictionaryDAO.java 
 * @Package com.digisky.dao.impl 
 * @Description: TODO
 * @author dengbin
 * @date 2014年11月26日 上午10:21:00 
 * @version V1.0   
 */
package com.digisky.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.digisky.dao.IDictionaryDAO;
import com.digisky.po.Dictionary;

/** 
 * @ClassName: DictionaryDAO 
 * @Description: TODO
 * @author dengbin
 * @date 2014年11月26日 上午10:21:00  
 */
@Repository
public class DictionaryDAO implements IDictionaryDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	/* (non-Javadoc)
	 * @see com.digisky.dao.IDictionaryDAO#add(com.digisky.po.Dictionary) 
	 */
	@Override
	public void add(Dictionary obj) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.digisky.dao.IDictionaryDAO#query(java.lang.String) 
	 */
	@Override
	public List<Dictionary> query(String code) {
		String hql="from Dictionary d where d.code = ? order by d.sort asc";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter(0, code);
		return query.list();
	}

}
