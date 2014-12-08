/**   
 * @Title: IOptLogService.java 
 * @Package com.digisky.service 
 * @Description: TODO
 * @author dengbin
 * @date 2014年12月3日 上午11:38:47 
 * @version V1.0   
 */
package com.digisky.log;

import java.util.List;

import com.digisky.po.OperateLog;

/** 
 * @ClassName: IOptLogService 
 * @Description: 操作日志业务处理，放置这里是为了aop拦截servic目录导致死循环
 * @author dengbin
 * @date 2014年12月3日 上午11:38:47  
 */
public interface IOptLogService {

	/**
	 * 
	 * @Title: save 
	 * @Description: 用户操作日志保存
	 * @author dengbin
	 * @date 2014年12月3日 下午6:07:31 
	 * @param log
	 */
	public void save(OperateLog log);
	
	/**
	 * 
	 * @Title: query 
	 * @Description: 用户操作日志查询
	 * @author dengbin
	 * @date 2014年12月3日 下午6:07:51 
	 * @return
	 */
	public List<OperateLog> query(String userName,String menuName,int page,int rows);
}
