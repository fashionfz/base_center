/**   
 * @Title: OptLogService.java 
 * @Package com.digisky.service.impl 
 * @Description: TODO
 * @author dengbin
 * @date 2014年12月3日 上午11:39:24 
 * @version V1.0   
 */
package com.digisky.log.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.digisky.dao.IOptLogDAO;
import com.digisky.log.IOptLogService;
import com.digisky.po.OperateLog;

/** 
 * @ClassName: OptLogService 
 * @Description: TODO
 * @author dengbin
 * @date 2014年12月3日 上午11:39:24  
 */
@Service
public class OptLogService implements IOptLogService{
	
	@Autowired
	private IOptLogDAO logDAO;

	/* (non-Javadoc)
	 * @see com.digisky.log.IOptLogService#save(com.digisky.po.OperateLog) 
	 */
	@Override
	@Transactional
	public void save(OperateLog log) {
		// TODO Auto-generated method stub
		logDAO.save(log);
	}

	/* (non-Javadoc)
	 * @see com.digisky.log.IOptLogService#query() 
	 */
	@Override
	@Transactional(readOnly=true)
	public List<OperateLog> query(String userName,String menuName,int page,int rows) {
		return logDAO.query(userName,menuName,page,rows);
	}


}
