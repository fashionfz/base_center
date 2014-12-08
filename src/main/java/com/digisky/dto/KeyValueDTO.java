/**   
 * @Title: KeyValueDTO.java 
 * @Package com.digisky.dto 
 * @Description: TODO
 * @author dengbin
 * @date 2014年11月26日 上午10:46:09 
 * @version V1.0   
 */
package com.digisky.dto;

/** 
 * @ClassName: KeyValueDTO 
 * @Description: 下来列表返回封装类
 * @author dengbin
 * @date 2014年11月26日 上午10:46:09  
 */
public class KeyValueDTO {

	private String key;
	
	private String value;
	
	private int disable;

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the disable
	 */
	public int getDisable() {
		return disable;
	}

	/**
	 * @param disable the disable to set
	 */
	public void setDisable(int disable) {
		this.disable = disable;
	}
	
	
}
