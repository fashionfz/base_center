package com.digisky.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.digisky.dao.IMenuDAO;
import com.digisky.po.RRoleMenu;
import com.digisky.po.SysMenu;

@Repository
public class MenuDAO implements IMenuDAO{

    @Autowired
    private SessionFactory sessionFactory;
    
    @SuppressWarnings("unchecked")
    @Override
    public List<SysMenu> queryAll() {
        Session session = sessionFactory.getCurrentSession();
        String hql="from SysMenu";
        Query query = session.createQuery(hql);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SysMenu> queryAnth(String roleId) {
        Session session = sessionFactory.getCurrentSession();
        String hql="select m from SysMenu m,RRoleMenu r where m.id=r.menuId and r.state=1 and r.roleId=?";
        Query query = session.createQuery(hql);
        query.setParameter(0, roleId);
        return query.list();
    }

    @Override
    public void create(SysMenu menu) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(menu);
    }

    @Override
    public void updateMenu(SysMenu menu) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(menu);
    }

    @Override
    public int deleteMenu(String menuId) {
        Session session = sessionFactory.getCurrentSession();
        String hql="delete from SysMenu m where m.id=?";
        Query query = session.createQuery(hql);
        query.setParameter(0, menuId);
        return query.executeUpdate();
    }

    @Override
    public void clearMenuR(String menuId) {
        Session session = sessionFactory.getCurrentSession();
        String hql="delete from RRoleMenu rrm where rrm.menuId=?";
        Query query = session.createQuery(hql);
        query.setParameter(0, menuId);
        query.executeUpdate();
    }

    @Override
    public List<SysMenu> queryByUser(String username) {
        Session session = sessionFactory.getCurrentSession();
        String sql="select distinct m.* from t_sys_menu m,r_role_menu rrm,t_sys_role r,r_user_role rur,t_sys_user u " +
        		"where m.ID=rrm.MENU_ID and rrm.ROLE_ID=r.ID and r.ID=rur.ROLE_ID and rur.USER_ID=u.ID and u.USER_NAME=? and m.isLeaf=?";
        SQLQuery query = session.createSQLQuery(sql).addEntity(SysMenu.class);
        query.setParameter(0, username);
        query.setParameter(1, false);
        return query.list();
    }

	/* (non-Javadoc)
	 * @see com.digisky.dao.IMenuDAO#getByParentId(java.lang.String) 
	 */
	@Override
	public List<SysMenu> getByParentId(String parentId) {
		String hql="";
		if(parentId == null || "".equals(parentId) || "null".equals(parentId)){
			hql="from SysMenu m where m.parentId is NULL";
		}else{
			hql="from SysMenu m where m.parentId = ? order by m.sort asc";
		}
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		if(parentId != null && !"".equals(parentId) && !"null".equals(parentId)){
			query.setParameter(0, parentId);
		}
		return query.list();
	}

	/* (non-Javadoc)
	 * @see com.digisky.dao.IMenuDAO#findById(java.lang.String) 
	 */
	@Override
	public SysMenu findById(String id) {
		Session session = sessionFactory.getCurrentSession();
		return (SysMenu) session.get(SysMenu.class, id);
	}

	/* (non-Javadoc)
	 * @see com.digisky.dao.IMenuDAO#getMaxSort(java.lang.String) 
	 */
	@Override
	public int getMaxSort(String parentId) {
		String hql="select max(m.sort) from SysMenu m where m.parentId=?";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter(0, parentId);
		return (Integer) query.uniqueResult();
	}

	/* (non-Javadoc)
	 * @see com.digisky.dao.IMenuDAO#queryNotLeaf() 
	 */
	@Override
	public List<SysMenu> queryNotLeaf() {
        Session session = sessionFactory.getCurrentSession();
        String hql="from SysMenu m where m.isLeaf = ?";
        Query query = session.createQuery(hql);
        query.setParameter(0, false);
        return query.list();
	}

	/* (non-Javadoc)
	 * @see com.digisky.dao.IMenuDAO#getByMenuId(java.lang.String) 
	 */
	@Override
	public List<RRoleMenu> getByMenuId(String menuId) {
		String hql="from RRoleMenu r where r.menuId=?";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter(0, menuId);
		return query.list();
	}
	
	

}
