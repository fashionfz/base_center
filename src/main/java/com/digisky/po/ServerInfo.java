package com.digisky.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * @ClassName: ServerInfo 
 * @Description: 服务器实体类
 * @author dengbin
 * @date 2014年11月25日 下午4:17:20
 */
@Entity
@Table(name="T_SERVER_INFO")
public class ServerInfo implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment") 
	@Column(name = "C_ID", unique = true, nullable = false) 
	private int id;
	@Column(name="SERVER_NAME",length=255)
	private String serverName;
	@Column(name="IP",length=20)
	private String ip;
	@Column(name="PORT")
	private int port;
	@Column(name="STATUS")
	private int status;
	@Column(name="REGISTER_COUNT")
	private int registerCount;
	@Column(name="ONLINE_COUNT")
	private int onlineCount;
	@Column(name="MAX_COUNT")
	private int maxCount;
	@Column(name="CREATE_TIME")
	private long createTime;
	@Column(name="LAST_UPDATE_TIME")
	private long lastUpdateTime;
	@Column(name="GAME_NAME",length=255)
	private String gameName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getRegisterCount() {
		return registerCount;
	}
	public void setRegisterCount(int registerCount) {
		this.registerCount = registerCount;
	}
	public int getOnlineCount() {
		return onlineCount;
	}
	public void setOnlineCount(int onlineCount) {
		this.onlineCount = onlineCount;
	}
	public int getMaxCount() {
		return maxCount;
	}
	public void setMaxCount(int maxCount) {
		this.maxCount = maxCount;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public long getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	/**
	 * @return the gameName
	 */
	public String getGameName() {
		return gameName;
	}
	/**
	 * @param gameName the gameName to set
	 */
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	
}
