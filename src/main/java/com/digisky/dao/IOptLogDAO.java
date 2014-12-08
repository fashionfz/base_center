/**   
 * @Title: IOptLogDAO.java 
 * @Package com.digisky.dao 
 * @Description: TODO
 * @author dengbin
 * @date 2014年12月3日 上午11:40:40 
 * @version V1.0   
 */
package com.digisky.dao;

import java.util.List;

import com.digisky.po.OperateLog;

/** 
 * @ClassName: IOptLogDAO 
 * @Description: TODO
 * @author dengbin
 * @date 2014年12月3日 上午11:40:40  
 */
public interface IOptLogDAO {

	/**
	 * 
	 * @Title: save 
	 * @Description: 新增操作日志
	 * @author dengbin
	 * @date 2014年12月3日 下午6:27:28 
	 * @param log
	 */
	public void save(OperateLog log);
	/**
	 * 
	 * @Title: query 
	 * @Description: 查询操作日子
	 * @author dengbin
	 * @date 2014年12月3日 下午6:27:39 
	 * @return
	 */
	public List<OperateLog> query(String userName,String menuName,int page,int rows);
}
