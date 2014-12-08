package com.digisky.exception;

/**
 * 
 * @ClassName: DirException 
 * @Description: 异常类
 * @author dengbin
 * @date 2014年11月25日 下午4:16:54
 */
public class DirException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private final String errorKey;
	
	public DirException(String errorKey){
		this.errorKey = errorKey;
	}
	
	public String getErrorKey(){
		return errorKey;
	}
}
