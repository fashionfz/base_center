/**   
 * @Title: MenuEnum.java 
 * @Package com.digisky.common 
 * @Description: TODO
 * @author dengbin
 * @date 2014年12月3日 上午11:19:28 
 * @version V1.0   
 */
package com.digisky.common;

/** 
 * @ClassName: MenuEnum 
 * @Description: 菜单对象枚举，操作日志注解使用
 * @author dengbin
 * @date 2014年12月3日 上午11:19:28  
 */
public enum MenuEnum {

	SERVER("服务器"),
	USER("用户管理");
	
	private String context;
	
	public String getContext(){
   	 	return this.context;
    }
    private MenuEnum(String context){
   	 	this.context = context;
    }
    
    public static void main(String[] args){
    	System.out.println(MenuEnum.USER.getContext());
    }
}
