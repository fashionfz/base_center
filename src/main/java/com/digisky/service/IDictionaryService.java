/**   
 * @Title: IDictionaryService.java 
 * @Package com.digisky.service 
 * @Description: TODO
 * @author dengbin
 * @date 2014年11月26日 上午10:14:37 
 * @version V1.0   
 */
package com.digisky.service;

import java.util.List;

import com.digisky.dto.KeyValueDTO;
import com.digisky.po.Dictionary;

/** 
 * @ClassName: IDictionaryService 
 * @Description: 数据字典业务类
 * @author dengbin
 * @date 2014年11月26日 上午10:14:37  
 */
public interface IDictionaryService {

	/**
	 * 
	 * @Title: add 
	 * @Description: 新增数据字典项
	 * @author dengbin
	 * @date 2014年12月4日 上午9:50:30 
	 * @param obj
	 */
	public void add(Dictionary obj);
	/**
	 * 
	 * @Title: getDictionary 
	 * @Description: 根据code类别查询数据字典
	 * @author dengbin
	 * @date 2014年12月4日 上午9:50:33 
	 * @param code 类别
	 * @param isFull 是否查询包含"-1（请选择）"项
	 * @return
	 */
	public List<KeyValueDTO> getDictionary(String code,boolean isFull);
	/**
	 * 
	 * @Title: query 
	 * @Description: 查询所有数据字典
	 * @author dengbin
	 * @date 2014年12月4日 上午9:50:36 
	 * @return
	 */
	public List<Dictionary> query();
}
