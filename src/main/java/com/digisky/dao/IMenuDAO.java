package com.digisky.dao;

import java.util.List;

import com.digisky.po.RRoleMenu;
import com.digisky.po.SysMenu;

public interface IMenuDAO {
	/**
	 * 
	 * @Title: queryAll 
	 * @Description: 查询所有，针对列表展现
	 * @author dengbin
	 * @date 2014年12月3日 下午6:11:41 
	 * @return
	 */
    public List<SysMenu> queryAll();
    /**
     * 
     * @Title: queryAnth 
     * @Description: 查询已授权给角色的功能，授权界面初始化展现
     * @author dengbin
     * @date 2014年12月3日 下午6:14:19 
     * @param roleId
     * @return
     */
    public List<SysMenu> queryAnth(String roleId);
    
    /**
     * 
     * @Title: queryByUser 
     * @Description: 根据用户查询授权的功能，首页功能菜单展现
     * @author dengbin
     * @date 2014年12月3日 下午6:16:57 
     * @param username
     * @return
     */
    public List<SysMenu> queryByUser(String username);
    
    /**
     * 
     * @Title: getByParentId 
     * @Description: 根据父id查询子功能，功能菜单树动态加载
     * @author dengbin
     * @date 2014年12月3日 下午6:17:35 
     * @param parentId
     * @return
     */
    public List<SysMenu> getByParentId(String parentId);
    /**
     * 
     * @Title: queryNotLeaf 
     * @Description: 查询非".do"结尾的功能，用于主功能菜单展现
     * @author dengbin
     * @date 2014年12月3日 下午6:22:49 
     * @return
     */
    public List<SysMenu> queryNotLeaf();
    /**
     * 
     * @Title: getByMenuId 
     * @Description: 查询父功能是否有授权，新增子功能时检查，如果有，子功能默认已授权
     * @author dengbin
     * @date 2014年12月3日 下午6:19:17 
     * @param menuId
     * @return
     */
    public List<RRoleMenu> getByMenuId(String menuId);
    /**
     * 
     * @Title: create 
     * @Description: 创建功能
     * @author dengbin
     * @date 2014年12月3日 下午6:20:33 
     * @param menu
     */
    public void create(SysMenu menu);
    /**
     * 
     * @Title: findById 
     * @Description: 根据id查找
     * @author dengbin
     * @date 2014年12月3日 下午6:20:51 
     * @param id
     * @return
     */
    public SysMenu findById(String id);
    /**
     * 
     * @Title: updateMenu 
     * @Description: 更新功能
     * @author dengbin
     * @date 2014年12月3日 下午6:21:03 
     * @param menu
     */
    public void updateMenu(SysMenu menu);
    /**
     * 
     * @Title: deleteMenu 
     * @Description: 删除功能
     * @author dengbin
     * @date 2014年12月3日 下午6:21:11 
     * @param menuId
     * @return
     */
    public int deleteMenu(String menuId);
    /**
     * 
     * @Title: clearMenuR 
     * @Description: 删除时，清除功能与角色的关系
     * @author dengbin
     * @date 2014年12月3日 下午6:21:18 
     * @param menuId
     */
    public void clearMenuR(String menuId);
    /**
     * 
     * @Title: getMaxSort 
     * @Description: 获取子功能目前最大的顺序号，新增功能时查询
     * @author dengbin
     * @date 2014年12月3日 下午6:21:53 
     * @param parentId
     * @return
     */
    public int getMaxSort(String parentId);
}
