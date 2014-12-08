package com.digisky.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.stereotype.Repository;

import com.digisky.dao.IServerInfoDAO;
import com.digisky.po.ServerInfo;

@Repository
public class ServerInfoDAO implements IServerInfoDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int add(ServerInfo server) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(server);
		return server.getId();
	}

	@Override
	public List<ServerInfo> getServers(String gameName,String serverName,int status,int page,int rows) {
		String hql="from ServerInfo s where 1=1";
		if(null!=gameName&&!"".equals(gameName)&&!"-1".equals(gameName)&&!"请选择".equals(gameName)){
			hql+=" and s.gameName = :gameName";
		}
		if(null != serverName && !"".equals(serverName)){
			hql+=" and s.serverName like :serverName";
		}
		if(status>=0){
			hql+=" and s.status = :status";
		}
		hql+=" order by s.createTime desc";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		if(null!=gameName&&!"".equals(gameName)&&!"-1".equals(gameName)&&!"请选择".equals(gameName)){
			query.setParameter("gameName", gameName);
		}
		if(null != serverName && !"".equals(serverName)){
			query.setParameter("serverName", serverName+"%");
		}
		if(status>=0){
			query.setParameter("status", status);
		}
		if(page>0&&rows>0){
            query.setFirstResult((page-1)*rows);
            query.setMaxResults(rows);
        }
		return query.list();
	}

	@Override
	public void update(ServerInfo server) {
		Session session = sessionFactory.getCurrentSession();
		session.update(server);
	}

	@Override
	public ServerInfo findByName(String serverName) {
		String hql="from ServerInfo s where s.serverName = ?";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter(0, serverName);
		@SuppressWarnings("unchecked")
		List<ServerInfo> list = query.list();
		if(null!=list&&list.size()>0)
			return list.get(0);
		else
			return null;
	}

	@Override
	public long queryCount(String gameName,String serverName,int status) {
		String hql="select count(s.id) from ServerInfo s where 1=1";
		if(null!=gameName&&!"".equals(gameName)&&!"-1".equals(gameName)&&!"请选择".equals(gameName)){
			hql+=" and s.gameName = :gameName";
		}
		if(null != serverName && !"".equals(serverName)){
			hql+=" and s.serverName like :serverName";
		}
		if(status>=0){
			hql+=" and s.status = :status";
		}
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		if(null!=gameName&&!"".equals(gameName)&&!"-1".equals(gameName)&&!"请选择".equals(gameName)){
			query.setParameter("gameName", gameName);
		}
		if(null != serverName && !"".equals(serverName)){
			query.setParameter("serverName", serverName+"%");
		}
		if(status>=0){
			query.setParameter("status", status);
		}
		return (Long) query.uniqueResult();
	}
	/* (non-Javadoc)
	 * @see com.digisky.dao.IServerInfoDAO#findById(int) 
	 */
	@Override
	public ServerInfo findById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (ServerInfo) session.get(ServerInfo.class, id);
	}

	/* (non-Javadoc)
	 * @see com.digisky.dao.IServerInfoDAO#getGameName() 
	 */
	@Override
	public List<String> getGameName() {
		String sql="select GAME_NAME FROM T_SERVER_INFO group by GAME_NAME";
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		return query.list();
	}

}
