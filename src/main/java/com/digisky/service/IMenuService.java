package com.digisky.service;

import java.util.List;
import java.util.Set;

import com.digisky.dto.EasyuiTreeDTO;
import com.digisky.po.SysMenu;
/**
 * 
 * @ClassName: IMenuService 
 * @Description: 功能业务处理类
 * @author dengbin
 * @date 2014年12月4日 上午9:52:26
 */
public interface IMenuService {

	/**
	 * 
	 * @Title: queryAll 
	 * @Description: 查询所有功能项
	 * @author dengbin
	 * @date 2014年12月4日 上午9:52:30 
	 * @return
	 */
    public List<SysMenu> queryAll();
    /**
     * 
     * @Title: create 
     * @Description: 新增功能项
     * @author dengbin
     * @date 2014年12月4日 上午9:52:33 
     * @param menu
     */
    public void create(SysMenu menu);
    /**
     * 
     * @Title: deleteMenu 
     * @Description: 删除功能项
     * @author dengbin
     * @date 2014年12月4日 上午9:52:36 
     * @param menuId
     * @return
     */
    public int deleteMenu(String menuId);
    /**
     * 
     * @Title: queryByUser 
     * @Description: 根据用户查询所有授权的功能项，首页功能菜单展现用，不包含叶子节点项和顶级首页项
     * @author dengbin
     * @date 2014年12月4日 上午9:52:38 
     * @param username 登录用户名
     * @return
     */
    public Set<EasyuiTreeDTO> queryByUser(String username);
    /**
     * 
     * @Title: init 
     * @Description: 系统启动时，初始化调用
     * @author dengbin
     * @date 2014年12月4日 上午9:52:41 
     * @param list
     */
    public void init(List<SysMenu> list);
    /**
     * 
     * @Title: getAsyncMenuTree 
     * @Description: 动态获取功能树，菜单功能管理页中展现
     * @author dengbin
     * @date 2014年12月4日 上午9:52:44 
     * @param parentId 父id
     * @return
     */
    public List<SysMenu> getAsyncMenuTree(String parentId);
    /**
     * 
     * @Title: getMenuTree 
     * @Description: 查询所有功能菜单树，管理员角色使用，首页功能菜单展现
     * @author dengbin
     * @date 2014年12月4日 上午9:52:47 
     * @return
     */
    public Set<EasyuiTreeDTO> getMenuTree();
    /**
     * 
     * @Title: getAuthMenuTree 
     * @Description: 查询包含了角色授权了的功能项，角色授权页展现
     * @author dengbin
     * @date 2014年12月4日 上午9:52:49 
     * @param username
     * @return
     */
    public Set<EasyuiTreeDTO> getAuthMenuTree(String username);
    /**
     * 
     * @Title: findById 
     * @Description: 根据id查找功能项
     * @author dengbin
     * @date 2014年12月4日 上午9:52:52 
     * @param id
     * @return
     */
    public SysMenu findById(String id);
    
}
