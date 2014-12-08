/**   
 * @Title: OperateLog.java 
 * @Package com.digisky.po 
 * @Description: TODO
 * @author dengbin
 * @date 2014年12月3日 上午10:05:31 
 * @version V1.0   
 */
package com.digisky.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/** 
 * @ClassName: OperateLog 
 * @Description: TODO
 * @author dengbin
 * @date 2014年12月3日 上午10:05:31  
 */
@Entity
@Table(name = "T_OPERATE_LOG")
public class OperateLog implements java.io.Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
    @Column(name = "ID", unique = true, nullable = false,length=32)
    private String id;
    @Column(name="USER_NAME",nullable = false,length=255)
    private String userName;
    @Column(name="OPT_TIME")
    private Date optTime;
    @Column(name="MENU_NAME",nullable = false,length=255)
    private String menuName;
    @Column(name="FUN_NAME",nullable = false,length=255)
    private String funName;
    @Column(name="TARGET_NAME",nullable = true,length=255)
    private String targetName;
    @Column(name="ARGS",nullable = true,length=1000)
    private String args;
    @Column(name="IS_SUCCESS")
    private boolean isSuccess;
    @Column(name="RESULT_MSG",nullable = true,length=1000)
    private String resultMssg;
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
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the args
	 */
	public String getArgs() {
		return args;
	}
	/**
	 * @param args the args to set
	 */
	public void setArgs(String args) {
		this.args = args;
	}
	/**
	 * @return the optTime
	 */
	public Date getOptTime() {
		return optTime;
	}
	/**
	 * @param optTime the optTime to set
	 */
	public void setOptTime(Date optTime) {
		this.optTime = optTime;
	}
	/**
	 * @return the menuName
	 */
	public String getMenuName() {
		return menuName;
	}
	/**
	 * @param menuName the menuName to set
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	/**
	 * @return the funName
	 */
	public String getFunName() {
		return funName;
	}
	/**
	 * @param funName the funName to set
	 */
	public void setFunName(String funName) {
		this.funName = funName;
	}
	/**
	 * @return the targetName
	 */
	public String getTargetName() {
		return targetName;
	}
	/**
	 * @param targetName the targetName to set
	 */
	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}
	/**
	 * @return the isSuccess
	 */
	public boolean isSuccess() {
		return isSuccess;
	}
	/**
	 * @param isSuccess the isSuccess to set
	 */
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	/**
	 * @return the resultMssg
	 */
	public String getResultMssg() {
		return resultMssg;
	}
	/**
	 * @param resultMssg the resultMssg to set
	 */
	public void setResultMssg(String resultMssg) {
		this.resultMssg = resultMssg;
	}
    
    
    
}
