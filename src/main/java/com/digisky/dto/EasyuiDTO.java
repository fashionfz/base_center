package com.digisky.dto;

import java.util.List;

/**
 * 
 * @ClassName: EasyuiDTO 
 * @Description: jquery easyui列表展现分页返回封装类
 * @author dengbin
 * @date 2014年11月25日 下午4:16:21
 */
public class EasyuiDTO {

	private long total;
	private List rows;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}
}
