/**   
 * @Title: DictionaryService.java 
 * @Package com.digisky.service.impl 
 * @Description: TODO
 * @author dengbin
 * @date 2014年11月26日 上午10:16:32 
 * @version V1.0   
 */
package com.digisky.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.digisky.common.Constant;
import com.digisky.dao.IDictionaryDAO;
import com.digisky.dto.KeyValueDTO;
import com.digisky.po.Dictionary;
import com.digisky.service.IDictionaryService;

/** 
 * @ClassName: DictionaryService 
 * @Description: TODO
 * @author dengbin
 * @date 2014年11月26日 上午10:16:32  
 */
@Service
public class DictionaryService implements IDictionaryService{
	
	@Autowired
	private IDictionaryDAO dictionaryDAO;

	/* (non-Javadoc)
	 * @see com.digisky.service.IDictionaryService#add(com.digisky.po.Dictionary) 
	 */
	@Override
	public void add(Dictionary obj) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.digisky.service.IDictionaryService#getDictionary(java.lang.String) 
	 */
	@Override
	@Transactional
	public List<KeyValueDTO> getDictionary(String code,boolean isFull) {
		List<KeyValueDTO> dtos = null;
		if(Constant.DICTIONARY_MAP.containsKey(code))
			dtos = Constant.DICTIONARY_MAP.get(code);
		else{
			List<Dictionary> list = dictionaryDAO.query(code);
			dtos = new ArrayList<KeyValueDTO>();
			for(Dictionary d : list){
				KeyValueDTO dto = new KeyValueDTO();
				dto.setKey(String.valueOf(d.getKey()));
				dto.setValue(d.getValue());
				dto.setDisable(d.getDisable());
				dtos.add(dto);
			}
			Constant.DICTIONARY_MAP.put(code, dtos);
		}
		
		if(isFull){
			return dtos;
		}else{
			List<KeyValueDTO> result = new ArrayList<KeyValueDTO>(dtos);
			for(KeyValueDTO dto : result){
				if(dto.getDisable()==1)
					result.remove(dto);
			}
			return result;
		}
	}

	/* (non-Javadoc)
	 * @see com.digisky.service.IDictionaryService#query() 
	 */
	@Override
	public List<Dictionary> query() {
		// TODO Auto-generated method stub
		return null;
	}

}
