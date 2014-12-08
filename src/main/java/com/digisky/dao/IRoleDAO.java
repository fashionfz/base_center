package com.digisky.dao;

import java.util.List;

import com.digisky.po.RRoleMenu;
import com.digisky.po.SysRole;

public interface IRoleDAO {

	/**
	 * 
	 * @Title: queryAll 
	 * @Description: 查询所有角色，列表展现
	 * @author dengbin
	 * @date 2014年12月4日 上午9:48:41 
	 * @return
	 */
    public List<SysRole> queryAll();
    /**
     * 
     * @Title: queryAnth 
     * @Description: 查询已授权用户的角色
     * @author dengbin
     * @date 2014年12月4日 上午9:49:15 
     * @param userId
     * @return
     */
    public List<SysRole> queryAnth(String userId);
    /**
     * 
     * @Title: queryUnAnth 
     * @Description: 查询未授权给用户的角色
     * @author dengbin
     * @date 2014年12月4日 上午9:49:18 
     * @param userId
     * @return
     */
    public List<SysRole> queryUnAnth(String userId);
    /**
     * 
     * @Title: querySecurity 
     * @Description: 初始化security使用
     * @author dengbin
     * @date 2014年12月4日 上午9:48:20 
     * @return
     */
    public List<Object[]> querySecurity();
    /**
     * 
     * @Title: save 
     * @Description: 新增角色
     * @author dengbin
     * @date 2014年12月4日 上午9:44:36 
     * @param role
     */
    public void save(SysRole role);
    /**
     * 
     * @Title: saveRoleAnth 
     * @Description: 保持角色授权
     * @author dengbin
     * @date 2014年12月4日 上午9:44:40 
     * @param rrm
     */
    public void saveRoleAnth(RRoleMenu rrm);
    /**
     * 
     * @Title: delRoleAnth 
     * @Description: 根据角色删除角色与功能关系
     * @author dengbin
     * @date 2014年12月4日 上午9:44:42 
     * @param roleId
     */
    public void delRoleAnth(String roleId);
    /**
     * 
     * @Title: deluserAnth 
     * @Description: 根据角色id删除用户与角色的关系
     * @author dengbin
     * @date 2014年12月4日 上午9:44:45 
     * @param roleId
     */
    public void deluserAnth(String roleId);
    /**
     * 
     * @Title: deleteRole 
     * @Description: 删除角色
     * @author dengbin
     * @date 2014年12月4日 上午9:44:49 
     * @param roleId
     * @return
     */
    public int deleteRole(String roleId);
    /**
     * 
     * @Title: updateRole 
     * @Description: 更新角色
     * @author dengbin
     * @date 2014年12月4日 上午9:44:53 
     * @param role
     */
    public void updateRole(SysRole role);
    
}
