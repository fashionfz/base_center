package com.digisky.dao;

import java.util.List;

import com.digisky.po.ServerInfo;

/**
 * 
 * @ClassName: IServerInfoDAO 
 * @Description: dao接口
 * @author dengbin
 * @date 2014年11月25日 下午4:30:49
 */
public interface IServerInfoDAO {
	/**
	 * 
	 * @Title: add 
	 * @Description: 添加服务器
	 * @author dengbin
	 * @date 2014年12月3日 下午6:28:14 
	 * @param server
	 * @return
	 */
	public int add(ServerInfo server);
	/**
	 * 
	 * @Title: update 
	 * @Description: 更新服务器
	 * @author dengbin
	 * @date 2014年12月3日 下午6:28:25 
	 * @param server
	 */
	public void update(ServerInfo server);
	/**
	 * 
	 * @Title: getServers 
	 * @Description: 查询服务器
	 * @author dengbin
	 * @date 2014年12月3日 下午6:28:34 
	 * @param serverName
	 * @param status
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<ServerInfo> getServers(String gameName,String serverName,int status,int page,int rows);
	/**
	 * 
	 * @Title: findByName 
	 * @Description: 根据名称查询服务器
	 * @author dengbin
	 * @date 2014年12月3日 下午6:28:43 
	 * @param serverName
	 * @return
	 */
	public ServerInfo findByName(String serverName);
	/**
	 * 
	 * @Title: findById 
	 * @Description: 根据主键id查询服务器
	 * @author dengbin
	 * @date 2014年12月3日 下午6:29:02 
	 * @param id
	 * @return
	 */
	public ServerInfo findById(int id);
	/**
	 * 
	 * @Title: queryCount 
	 * @Description: 查询总行数，分页使用
	 * @author dengbin
	 * @date 2014年12月3日 下午6:29:13 
	 * @param serverName
	 * @param status
	 * @return
	 */
	public long queryCount(String gameName,String serverName,int status);
	/**
	 * 获取游戏名称列表
	 * @Title: getGameName 
	 * @Description: TODO
	 * @author dengbin
	 * @date 2014年12月4日 下午4:50:02 
	 * @return
	 */
	public List<String> getGameName();
}
