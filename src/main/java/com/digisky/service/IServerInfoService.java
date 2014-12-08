package com.digisky.service;

import java.util.List;

import com.digisky.dto.KeyValueDTO;
import com.digisky.po.ServerInfo;

/**
 * 
 * @ClassName: IServerInfoService 
 * @Description: 服务器业务处理接口
 * @author dengbin
 * @date 2014年11月25日 下午4:18:57
 */
public interface IServerInfoService {

	/**
	 * 
	 * @Title: register 
	 * @Description: 服务器上报注册
	 * @author dengbin
	 * @date 2014年11月25日 下午4:19:18 
	 * @param server 服务器对象
	 */
	public void register(ServerInfo server);
	/**
	 * 
	 * @Title: getServers 
	 * @Description: 查询所有服务
	 * @author dengbin
	 * @date 2014年12月4日 上午10:05:53 
	 * @param serverName 服务器名称
	 * @param status 服务器状态
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<ServerInfo> getServers(String gameName,String serverName,int status,int page,int rows);
	/**
	 * 
	 * @Title: queryCount 
	 * @Description: 查询服务器数量
	 * @author dengbin
	 * @date 2014年12月4日 上午10:06:19 
	 * @param serverName 服务器名称
	 * @param status 服务器状态
	 * @return
	 */
	public long queryCount(String gameName,String serverName,int status);
	/**
	 * 
	 * @Title: modifStatus 
	 * @Description: 修改服务器状态
	 * @author dengbin
	 * @date 2014年11月25日 下午4:20:06 
	 * @param id
	 * @param status
	 * @return
	 */
	public void modifStatus(int id,int status);
	/**
	 * 获取游戏名称列表
	 * @Title: getGameName 
	 * @Description: TODO
	 * @author dengbin
	 * @date 2014年12月4日 下午4:50:02 
	 * @return
	 */
	public List<KeyValueDTO> getGameName();
}
