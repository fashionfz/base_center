package com.digisky.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digisky.dto.EasyuiTreeDTO;
import com.digisky.po.SysMenu;
import com.digisky.service.IMenuService;
/**
 * 
 * @ClassName: MenuController 
 * @Description: 功能菜单处理action
 * @author dengbin
 * @date 2014年12月4日 上午10:28:05
 */
@Controller
public class MenuController {

    @Autowired
    private IMenuService menuService;
    /**
     * 
     * @Title: queryAll 
     * @Description: 查询所有功能菜单
     * @author dengbin
     * @date 2014年12月4日 上午10:28:21 
     * @return
     */
    @RequestMapping(value="/queryMenu")
    public @ResponseBody List<SysMenu> queryAll(){
        List<SysMenu> list = menuService.queryAll();
        return list;
    }
    /**
     * 
     * @Title: create 
     * @Description: 新增、修改功能菜单
     * @author dengbin
     * @date 2014年12月4日 上午10:28:24 
     * @param menu
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addMenu",method = {RequestMethod.POST},headers = {"content-type=application/json","content-type=application/xml"})
    @ResponseBody
    public SysMenu create(@RequestBody SysMenu menu) throws Exception{
        menuService.create(menu);
        return menu; 
    }
    /**
     * 
     * @Title: deleteUser 
     * @Description: 删除功能菜单
     * @author dengbin
     * @date 2014年12月4日 上午10:28:27 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/deleteMenu")
    public @ResponseBody int deleteMenu(HttpServletRequest request,HttpServletResponse response){
        String menuId=request.getParameter("menuId");
        if(menuId!=null&&!"".equals(menuId)){
            return menuService.deleteMenu(menuId);
        }else{
            return 0;
        }
    }
    /**
     * 
     * @Title: queryMenu 
     * @Description: 根据用户查询用户所授权的功能菜单
     * @author dengbin
     * @date 2014年12月4日 上午10:28:29 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/queryMenuByUser")
    public @ResponseBody Set<EasyuiTreeDTO> queryMenu(HttpServletRequest request,HttpServletResponse response){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        Set<EasyuiTreeDTO> list = null;
        if("admin".equals(username)){
        	list = menuService.getMenuTree();
        }else{
        	list = menuService.queryByUser(username);
        }
    	if(list != null && list.size()>0)
    		return list.iterator().next().getChildren();
    	else
    		return null;
        	
    }
    /**
     * 
     * @Title: getAsyncMenuTree 
     * @Description: 动态查询功能菜单树
     * @author dengbin
     * @date 2014年12月4日 上午10:28:33 
     * @param request
     * @return
     */
    @RequestMapping(value="/getAsyncMenuTree")
    public @ResponseBody List<SysMenu> getAsyncMenuTree(HttpServletRequest request){
    	String parentId = request.getParameter("id");
    	return menuService.getAsyncMenuTree(parentId);
    }
    /**
     * 
     * @Title: getAuthMenuTree 
     * @Description: 查询角色授权的功能菜单
     * @author dengbin
     * @date 2014年12月4日 上午10:28:36 
     * @param request
     * @return
     */
    @RequestMapping(value="/getAuthMenuTree")
    public @ResponseBody Set<EasyuiTreeDTO> getAuthMenuTree(HttpServletRequest request){
        String roleId = request.getParameter("roleId");
    	String id = request.getParameter("id");
    	if(id==null || "".equals(id) || "null".equals(id)){
        	Set<EasyuiTreeDTO> list = menuService.getAuthMenuTree(roleId);
        	if(list != null)
        		return list;
        	else
        		return new HashSet<EasyuiTreeDTO>();
    	}else
    		return new HashSet<EasyuiTreeDTO>();
    }
    /**
     * 
     * @Title: getMenuById 
     * @Description: 根据id查询功能菜单
     * @author dengbin
     * @date 2014年12月4日 上午10:28:39 
     * @param request
     * @return
     */
    @RequestMapping(value="/getMenuById")
    public @ResponseBody SysMenu getMenuById(HttpServletRequest request){
    	String id = request.getParameter("id");
    	return menuService.findById(id);
    }
}
