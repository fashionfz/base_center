/**   
 * @Title: OptLogController.java 
 * @Package com.digisky.controller 
 * @Description: TODO
 * @author dengbin
 * @date 2014年12月3日 下午2:22:39 
 * @version V1.0   
 */
package com.digisky.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digisky.log.IOptLogService;
import com.digisky.po.OperateLog;

/** 
 * @ClassName: OptLogController 
 * @Description: 操作日志处理action
 * @author dengbin
 * @date 2014年12月3日 下午2:22:39  
 */
@Controller
public class OptLogController {
	
	@Autowired
	private IOptLogService logService;
	/**
	 * 
	 * @Title: query 
	 * @Description: 操作日志查询
	 * @author dengbin
	 * @date 2014年12月4日 上午10:27:15 
	 * @return
	 */
    @RequestMapping(value="/queryOptLog")
	public @ResponseBody List<OperateLog> query(HttpServletRequest request){
        String page = request.getParameter("page");
        String rows = request.getParameter("rows");
        String menuName = request.getParameter("menuName");
        String userName = request.getParameter("userName");
		return logService.query(userName,menuName,Integer.parseInt(page),Integer.parseInt(rows));
	}

}
