/**   
 * @Title: IDictionaryDAO.java 
 * @Package com.digisky.dao 
 * @Description: TODO
 * @author dengbin
 * @date 2014年11月26日 上午10:20:04 
 * @version V1.0   
 */
package com.digisky.dao;

import java.util.List;

import com.digisky.po.Dictionary;

/** 
 * @ClassName: IDictionaryDAO 
 * @Description: TODO
 * @author dengbin
 * @date 2014年11月26日 上午10:20:04  
 */
public interface IDictionaryDAO {

	/**
	 * 
	 * @Title: add 
	 * @Description: 新增数据字典
	 * @author dengbin
	 * @date 2014年12月3日 下午6:26:45 
	 * @param obj
	 */
	public void add(Dictionary obj);
	
	/**
	 * 
	 * @Title: query 
	 * @Description: 根据code查询数据字典
	 * @author dengbin
	 * @date 2014年12月3日 下午6:26:59 
	 * @param code
	 * @return
	 */
	public List<Dictionary> query(String code);
}
