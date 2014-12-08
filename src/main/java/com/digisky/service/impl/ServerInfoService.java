package com.digisky.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.digisky.common.FunEnum;
import com.digisky.common.MenuEnum;
import com.digisky.dao.IServerInfoDAO;
import com.digisky.dto.KeyValueDTO;
import com.digisky.log.OperateLog;
import com.digisky.log.OptLogUtil;
import com.digisky.po.ServerInfo;
import com.digisky.service.IServerInfoService;

/**
 * 
 * @ClassName: ServerInfoService 
 * @Description: 服务器处理业务逻辑类
 * @author dengbin
 * @date 2014年11月25日 下午4:17:44
 */
@Service
public class ServerInfoService implements IServerInfoService{

	@Autowired
	private IServerInfoDAO serverInfoDAO;

	/* (non-Javadoc)
	 * @see com.digisky.service.IServerInfoService#register(com.digisky.po.ServerInfo) 
	 */
	@Override
	@Transactional
	public void register(ServerInfo server) {
		ServerInfo oldServer = serverInfoDAO.findByName(server.getServerName());
		if(oldServer != null){
			oldServer.setLastUpdateTime(new Date().getTime());
			oldServer.setIp(server.getIp());
			oldServer.setMaxCount(server.getMaxCount());
			oldServer.setOnlineCount(server.getOnlineCount());
			oldServer.setPort(server.getPort());
			oldServer.setRegisterCount(server.getRegisterCount());
			oldServer.setStatus(server.getStatus());
			serverInfoDAO.update(oldServer);
		}else{
			Long time = new Date().getTime();
			server.setCreateTime(time);
			server.setLastUpdateTime(time);
			serverInfoDAO.add(server);
		}
	}

	/* (non-Javadoc)
	 * @see com.digisky.service.IServerInfoService#getServers(java.lang.String, int, int) 
	 */
	@Override
	@Transactional(readOnly=true)
	public List<ServerInfo> getServers(String gameName,String serverName,int status,int page,int rows) {
		return serverInfoDAO.getServers(gameName,serverName,status,page,rows);
	}

	/* (non-Javadoc)
	 * @see com.digisky.service.IServerInfoService#queryCount(java.lang.String) 
	 */
	@Override
	@Transactional(readOnly=true)
	public long queryCount(String gameName,String serverName,int status) {
		return serverInfoDAO.queryCount(gameName,serverName,status);
	}

	/* (non-Javadoc)
	 * @see com.digisky.service.IServerInfoService#modifStatus(int, int) 
	 */
	@Override
	@Transactional
	@OperateLog(menuName=MenuEnum.SERVER,funName=FunEnum.UPDATE)
	public void modifStatus(int id, int status) {
		ServerInfo info = serverInfoDAO.findById(id);
		if(info!=null){
			OptLogUtil.setTargetName(info.getServerName());
			info.setStatus(status);
			serverInfoDAO.update(info);
		}
	}

	/* (non-Javadoc)
	 * @see com.digisky.service.IServerInfoService#getGameName() 
	 */
	@Override
	@Transactional(readOnly=true)
	public List<KeyValueDTO> getGameName() {
		List<String> list = serverInfoDAO.getGameName();
		List<KeyValueDTO> result = new ArrayList<KeyValueDTO>();
		KeyValueDTO dto2 = new KeyValueDTO();
		dto2.setKey("-1");
		dto2.setValue("请选择");
		result.add(dto2);
 		if(null != list && list.size()>0){
 			for(String name : list){
 				KeyValueDTO dto = new KeyValueDTO();
 	 			dto.setKey(name);
 	 			dto.setValue(name);
 	 			result.add(dto);
 			}
		}
		return result;
	}
}
