package com.digisky.dto;

/**
 * 
 * @ClassName: ResultDTO 
 * @Description: http请求返回封装类
 * @author dengbin
 * @date 2014年11月25日 下午4:15:43
 */
public class ResultDTO {

	private int code;
	private Object data;
	
	public ResultDTO(){
		
	}
	
	public ResultDTO(int code,Object data){
		this.code = code;
		this.data = data;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
