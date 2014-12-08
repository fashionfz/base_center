/**   
 * @Title: Dictionary.java 
 * @Package com.digisky.po 
 * @Description: TODO
 * @author dengbin
 * @date 2014年11月26日 上午10:08:05 
 * @version V1.0   
 */
package com.digisky.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/** 
 * @ClassName: Dictionary 
 * @Description: 数据字典
 * @author dengbin
 * @date 2014年11月26日 上午10:08:05  
 */
@Entity
@Table(name = "T_DICTIONARY")
public class Dictionary implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment") 
	@Column(name = "C_ID", unique = true, nullable = false) 
	private int id;
	@Column(name="CODE",length=255)
	private String code;
	@Column(name="NAME",length=255)
	private String name;
	@Column(name="KEY")
	private int key;
	@Column(name="VALUE",length=255)
	private String value;
	@Column(name="SORT")
	private int sort;
	@Column(name="DISABLE")
	private int disable;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the key
	 */
	public int getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(int key) {
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
	 * @return the sort
	 */
	public int getSort() {
		return sort;
	}
	/**
	 * @param sort the sort to set
	 */
	public void setSort(int sort) {
		this.sort = sort;
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
