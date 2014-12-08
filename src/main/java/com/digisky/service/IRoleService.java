package com.digisky.service;

import java.util.List;

import com.digisky.po.SysRole;
/**
 * 
 * @ClassName: IRoleService 
 * @Description: 角色业务处理类
 * @author dengbin
 * @date 2014年12月4日 上午9:59:56
 */
public interface IRoleService {

	/**
	 * 
	 * @Title: queryAll 
	 * @Description: 查询所有角色
	 * @author dengbin
	 * @date 2014年12月4日 上午9:59:59 
	 * @return 角色集合
	 */
    public List<SysRole> queryAll();
    /**
     * 
     * @Title: queryAnth 
     * @Description: 根据用户id查询用户授权的角色
     * @author dengbin
     * @date 2014年12月4日 上午10:00:02 
     * @param userId 用户id
     * @return 角色集合
     */
    public List<SysRole> queryAnth(String userId);
    /**
     * 
     * @Title: queryUnAnth 
     * @Description: 根据用户id查询用户未授权的角色
     * @author dengbin
     * @date 2014年12月4日 上午10:00:05 
     * @param userId 用户id
     * @return 角色集合
     */
    public List<SysRole> queryUnAnth(String userId);
    /**
     * 
     * @Title: save 
     * @Description: 新增、更新角色
     * @author dengbin
     * @date 2014年12月4日 上午10:00:07 
     * @param role 角色对象
     */
    public void save(SysRole role);
    /**
     * 
     * @Title: roleAnthMenu 
     * @Description: 角色授权功能保存
     * @author dengbin
     * @date 2014年12月4日 上午10:00:10 
     * @param roleId 角色id
     * @param checked 页面周选中的功能
     * @param indeterminate 页面中不确定的功能
     */
    public void roleAnthMenu(String roleId,String[] checked,String[] indeterminate);
    /**
     * 
     * @Title: deleteRole 
     * @Description: 删除角色
     * @author dengbin
     * @date 2014年12月4日 上午10:00:13 
     * @param roleId 角色id
     * @return 删除的行数
     */
    public int deleteRole(String roleId);
    
}
