/**   
 * @Title: OptLogUtil.java 
 * @Package com.digisky.log 
 * @Description: TODO
 * @author dengbin
 * @date 2014年12月3日 上午10:01:15 
 * @version V1.0   
 */
package com.digisky.log;

import com.digisky.po.OperateLog;

/** 
 * @ClassName: OptLogUtil 
 * @Description: 操作日志处理工具类
 * @author dengbin
 * @date 2014年12月3日 上午10:01:15  
 */
public class OptLogUtil {

	/**
	 * 
	 * @Title: setTargetName 
	 * @Description: 设置操作目标对象，如果用目标对象才设置
	 * @author dengbin
	 * @date 2014年12月4日 上午10:10:15 
	 * @param targetName
	 */
	public static void setTargetName(String targetName){
		OperateLog log =OptLogAop.optLog.get();
		if(log!=null){
			log.setTargetName(targetName);
		}
	}
	/**
	 * 
	 * @Title: setSuccess 
	 * @Description: 设置操作日志操作结果，如果失败或异常可以设置为失败，默认是成功的
	 * @author dengbin
	 * @date 2014年12月4日 上午10:10:18 
	 * @param flag
	 */
	public static void setSuccess(boolean flag){
		OperateLog log =OptLogAop.optLog.get();
		if(log!=null){
			log.setSuccess(flag);
		}
	}
}
