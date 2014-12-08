package com.digisky.service;

import java.util.List;

import com.digisky.po.SysUser;
/**
 * 
 * @ClassName: IUserService 
 * @Description: 系统用户业务处理类
 * @author dengbin
 * @date 2014年12月4日 上午10:06:56
 */
public interface IUserService {
	
	/**
	 * 
	 * @Title: queryAll 
	 * @Description: 查询所有用户
	 * @author dengbin
	 * @date 2014年12月4日 上午10:07:02 
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<SysUser> queryAll(int page,int rows);
	/**
	 * 
	 * @Title: create 
	 * @Description: 新增，修改用户
	 * @author dengbin
	 * @date 2014年12月4日 上午10:07:07 
	 * @param user
	 * @return
	 */
	public int create(SysUser user);
	/**
	 * 
	 * @Title: getAllCount 
	 * @Description: 查询用户总行数
	 * @author dengbin
	 * @date 2014年12月4日 上午10:07:09 
	 * @return
	 */
	public long getAllCount();
	/**
	 * 
	 * @Title: userAnthRole 
	 * @Description: 保存用户授权
	 * @author dengbin
	 * @date 2014年12月4日 上午10:07:12 
	 * @param userId
	 * @param roleIds
	 */
	public void userAnthRole(String userId,String[] roleIds);
	/**
	 * 
	 * @Title: deleteUser 
	 * @Description: 删除用户
	 * @author dengbin
	 * @date 2014年12月4日 上午10:07:14 
	 * @param userId
	 * @return
	 */
	public int deleteUser(String userId);
	/**
	 * 
	 * @Title: init 
	 * @Description: 系统启动时，初始化调用
	 * @author dengbin
	 * @date 2014年12月4日 上午10:07:17 
	 * @param user
	 */
	public void init(SysUser user);
}
