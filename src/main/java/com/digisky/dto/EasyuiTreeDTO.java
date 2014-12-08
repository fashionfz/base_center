/**   
 * @Title: EasyuiTreeDTO.java 
 * @Package com.digisky.dto 
 * @Description: TODO
 * @author dengbin
 * @date 2014年12月1日 上午11:34:13 
 * @version V1.0   
 */
package com.digisky.dto;

import java.util.Set;

/** 
 * @ClassName: EasyuiTreeDTO 
 * @Description: easyui tree对象返回封装类
 * @author dengbin
 * @date 2014年12月1日 上午11:34:13  
 */
public class EasyuiTreeDTO implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String parentId;
	
	private String text;
	
	private String iconCls;
	
	private String url;
	
	private Set<EasyuiTreeDTO> children;
	
	private Boolean checked;
	
	private Boolean leaf;
	
	private String state;
	
	private int sort;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the iconCls
	 */
	public String getIconCls() {
		return iconCls;
	}

	/**
	 * @param iconCls the iconCls to set
	 */
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}



	/**
	 * @return the children
	 */
	public Set<EasyuiTreeDTO> getChildren() {
		return children;
	}

	/**
	 * @param children the children to set
	 */
	public void setChildren(Set<EasyuiTreeDTO> children) {
		this.children = children;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the checked
	 */
	public Boolean getChecked() {
		return checked;
	}

	/**
	 * @param checked the checked to set
	 */
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	/**
	 * @return the leaf
	 */
	public Boolean getLeaf() {
		return leaf;
	}

	/**
	 * @param leaf the leaf to set
	 */
	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
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
	
	
}
