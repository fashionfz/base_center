package com.digisky.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.digisky.dto.KeyValueDTO;

/**
 * 
* @ClassName: Constant 
* @Description: 静态常量类
* @author dengbin
* @date 2014年11月25日 下午4:01:22 
*
 */
public class Constant {

	/**HTTP请求返回成功*/
	public static final int REQUEST_SUCCESS = 0;
	/**HTTP请求返回失败*/
	public static final int REQUEST_FAIL = 1;
	/**权限验证失败*/
	public static final int SECURITY_FAIL = 403;
	/**缓存数据字典*/
	public static final Map<String,List<KeyValueDTO>> DICTIONARY_MAP = new HashMap<String,List<KeyValueDTO>>();
}
