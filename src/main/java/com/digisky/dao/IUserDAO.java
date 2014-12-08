package com.digisky.dao;

import java.util.List;

import com.digisky.po.RUserRole;
import com.digisky.po.SysUser;

public interface IUserDAO {

    /**
     * 
     * @Title: getAllCount 
     * @Description: 分页查询总记录行数
     * @author dengbin
     * @date 2014年12月4日 上午9:36:43 
     * @return
     */
    public long getAllCount();
	/**
	 * 
	 * @Title: queryAll 
	 * @Description: 查询用户，列表展现
	 * @author dengbin
	 * @date 2014年12月3日 下午6:30:51 
	 * @param page
	 * @param rows
	 * @return
	 */
    public List<SysUser> queryAll(int page,int rows);
    /**
     * 
     * @Title: findById 
     * @Description: 根据id查找用户
     * @author dengbin
     * @date 2014年12月3日 下午6:31:06 
     * @param id
     * @return
     */
    public SysUser findById(String id);
    /**
     * 
     * @Title: create 
     * @Description: 新增用户
     * @author dengbin
     * @date 2014年12月4日 上午9:36:38 
     * @param user
     */
    public void create(SysUser user);
    /**
     * 
     * @Title: saveUserAnth 
     * @Description: 保持用户授权
     * @author dengbin
     * @date 2014年12月4日 上午9:36:46 
     * @param rur
     */
    public void saveUserAnth(RUserRole rur);
    /**
     * 
     * @Title: delUserAnth 
     * @Description: 删除用户授权
     * @author dengbin
     * @date 2014年12月4日 上午9:36:49 
     * @param userId
     */
    public void delUserAnth(String userId);
    /**
     * 
     * @Title: deleteUser 
     * @Description: 删除用户
     * @author dengbin
     * @date 2014年12月4日 上午9:36:51 
     * @param userId
     * @return
     */
    public int deleteUser(String userId);
    /**
     * 
     * @Title: updateUser 
     * @Description:更新用户
     * @author dengbin
     * @date 2014年12月4日 上午9:36:54 
     * @param user
     */
    public void updateUser(SysUser user);
    
    
}
