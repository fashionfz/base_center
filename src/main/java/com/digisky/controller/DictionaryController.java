/**   
 * @Title: DictionaryController.java 
 * @Package com.digisky.controller 
 * @Description: TODO
 * @author dengbin
 * @date 2014年11月26日 上午10:32:24 
 * @version V1.0   
 */
package com.digisky.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digisky.dto.KeyValueDTO;
import com.digisky.service.IDictionaryService;

/** 
 * @ClassName: DictionaryController 
 * @Description: 数据字典action
 * @author dengbin
 * @date 2014年11月26日 上午10:32:24  
 */
@Controller
public class DictionaryController {
	
	@Autowired
	private IDictionaryService dictionaryService;
	
	/**
	 * 
	 * @Title: getDictionary 
	 * @Description: 用户数据展现的，包含了禁用的项
	 * @author dengbin
	 * @date 2014年11月26日 下午12:22:24 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getDictionary") 
	@ResponseBody 
	public List<KeyValueDTO> getDictionary(HttpServletRequest request){
		String code = request.getParameter("code");
		String isFull = request.getParameter("isFull");
		List<KeyValueDTO> list = dictionaryService.getDictionary(code,Boolean.parseBoolean(isFull));
		return list;
	}
}
