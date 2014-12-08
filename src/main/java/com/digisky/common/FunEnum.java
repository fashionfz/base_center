/**   
 * @Title: FunEnum.java 
 * @Package com.digisky.common 
 * @Description: TODO
 * @author dengbin
 * @date 2014年12月3日 上午11:19:55 
 * @version V1.0   
 */
package com.digisky.common;

/** 
 * @ClassName: FunEnum 
 * @Description: 功能枚举，操作日志注解使用
 * @author dengbin
 * @date 2014年12月3日 上午11:19:55  
 */
public enum FunEnum {
	QUERY("查询"),
	SAVE("保存"),
	UPDATE("更新");
	
	private String context;
	
	public String getContext(){
   	 	return this.context;
    }
    private FunEnum(String context){
   	 	this.context = context;
    }
}
