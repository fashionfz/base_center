package com.digisky.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digisky.common.Constant;
import com.digisky.dto.EasyuiDTO;
import com.digisky.dto.KeyValueDTO;
import com.digisky.dto.ResultDTO;
import com.digisky.exception.DirException;
import com.digisky.po.ServerInfo;
import com.digisky.service.IServerInfoService;
import com.digisky.util.HttpUtil;

/**
 * 
 * @ClassName: ServerInfoController 
 * @Description: 服务器处理controller 
 * @author dengbin
 * @date 2014年11月25日 下午4:10:45 
 *
 */
@Controller
public class ServerInfoController {
	
	private final Logger log = Logger.getLogger(ServerInfoController.class);
	
	@Autowired
	private IServerInfoService serverInfo;

	/**
	 * 
	 * @Title: register 
	 * @Description: 服务器注册
	 * @author dengbin
	 * @date 2014年11月25日 下午4:10:15 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/register") 
	@ResponseBody 
	public ResultDTO register(HttpServletRequest request) throws Exception{
		String serverName = request.getParameter("serverName");
		String registeCount = request.getParameter("registeCount");
		String onlineCount = request.getParameter("onlineCount");
		String maxCount = request.getParameter("maxCount");
		String status = request.getParameter("status");
		String ip = request.getParameter("ip");
		String port = request.getParameter("port");
		
		if(null==serverName||"".equals(serverName))
			throw new DirException("SERVER_NAME_IS_NULL");
		if(null==status || "".equals(status))
			throw new DirException("STATUS_IS_NULL");
		if(null==registeCount || "".equals(registeCount))
			throw new DirException("REGISTE_COUNT_IS_NULL");
		if(null==onlineCount||"".equals(onlineCount))
			throw new DirException("ONLINE_COUNT_IS_NULL");
		if(null==maxCount || "".equals(maxCount))
			throw new DirException("MAX_COUNT_IS_NULL");
		
		ServerInfo server = new ServerInfo();
		server.setServerName(serverName);
		if(null==ip||"".equals(ip))
			server.setIp(HttpUtil.getIpAddr(request));
		else
			server.setIp(ip);
		if(null!=port&&!"".equals(port))
			server.setPort(Integer.parseInt(port));
		
		server.setStatus(Integer.parseInt(status));
		server.setRegisterCount(Integer.parseInt(registeCount));
		server.setOnlineCount(Integer.parseInt(onlineCount));
		server.setMaxCount(Integer.parseInt(maxCount));
		serverInfo.register(server);
		log.info(server.getServerName()+"上报成功！");
		ResultDTO result = new ResultDTO(Constant.REQUEST_SUCCESS,"");
		return result;
	}
	
	/**
	 * 
	 * @Title: getServers 
	 * @Description: 查询所有服务器
	 * @author dengbin
	 * @date 2014年11月25日 下午4:09:07 
	 * @return
	 */
	@RequestMapping(value = "/getServers") 
	@ResponseBody 
	public ResultDTO getServers(HttpServletRequest request){
		String  gameName = request.getParameter("gameName");
		List<ServerInfo> list = serverInfo.getServers(gameName,null,-1,0,0);
		ResultDTO result = new ResultDTO(Constant.REQUEST_SUCCESS,list);
		return result;
	}
	
	
	/**
	 * 
	 * @Title: query 
	 * @Description: 后台页面查询服务总数 
	 * @author dengbin
	 * @date 2014年11月25日 下午4:09:07 
	 * @param request
	 * @return
	 */
    @RequestMapping(value="/queryServer",method = {RequestMethod.POST})
    public @ResponseBody EasyuiDTO query(HttpServletRequest request){
        String serverName = request.getParameter("serverName");
        String status = request.getParameter("status");
        String gameName = request.getParameter("gameName");
        String page = request.getParameter("page");
        String rows = request.getParameter("rows");
        long count = serverInfo.queryCount(gameName,serverName,Integer.parseInt(status));
        List<ServerInfo> list = serverInfo.getServers(gameName,serverName,Integer.parseInt(status),Integer.parseInt(page),Integer.parseInt(rows));
        EasyuiDTO result = new EasyuiDTO();
        result.setTotal(count);
        result.setRows(list);
        return result;
    }
    
    /**
     * 
     * @Title: modifStatus 
     * @Description: 修改服务器状态 
	 * @author dengbin
	 * @date 2014年11月25日 下午4:09:07 
     * @param request
     * @return
     * @throws DirException
     */
    @RequestMapping(value="/modifyStatus")
    @ResponseBody
    public int modifStatus(HttpServletRequest request) throws DirException {
    	String id = request.getParameter("id");
    	String serverName = request.getParameter("serverName");
    	String status = request.getParameter("status");
    	if(null == id || "".equals(id))
    		throw new DirException("SERVER_ID_IS_NULL");
    	if(null == status || "".equals(status))
    		throw new DirException("STATUS_IS_NULL");
    	serverInfo.modifStatus(Integer.parseInt(id), Integer.parseInt(status));
    	return 1;
    }
    @RequestMapping(value="/getGameNameList")
    public @ResponseBody List<KeyValueDTO> getGameNameList(){
    	return serverInfo.getGameName();
    }
}
