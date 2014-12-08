package com.digisky.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.digisky.po.SysRole;
import com.digisky.service.IRoleService;
/**
 * 
 * @ClassName: RoleController 
 * @Description: 角色处理action
 * @author dengbin
 * @date 2014年12月4日 上午10:32:22
 */
@Controller
public class RoleController {

    @Autowired
    private IRoleService roleService;
    
    /**
     * 
     * @Title: queryAll 
     * @Description: 查询所有角色
     * @author dengbin
     * @date 2014年12月4日 上午10:32:27 
     * @return
     */
    @RequestMapping(value="/queryRole")
    public @ResponseBody List<SysRole> queryAll(){
        return roleService.queryAll();
    }
    /**
     * 
     * @Title: queryAnth 
     * @Description: 根据用户id查询用户授权的角色
     * @author dengbin
     * @date 2014年12月4日 上午10:32:30 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/queryAnthRole")
    public @ResponseBody List<SysRole> queryAnth(HttpServletRequest request,HttpServletResponse response){
        String userId = request.getParameter("userId");
        return roleService.queryAnth(userId);
    }
    /**
     * 
     * @Title: queryUnAnth 
     * @Description: 根据用户id查询用户未授权的角色
     * @author dengbin
     * @date 2014年12月4日 上午10:32:33 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/queryUnAnthRole")
    public @ResponseBody List<SysRole> queryUnAnth(HttpServletRequest request,HttpServletResponse response){
        String userId = request.getParameter("userId");
        return roleService.queryUnAnth(userId);
    }
    /**
     * 
     * @Title: addRole 
     * @Description: 新增、修改角色
     * @author dengbin
     * @date 2014年12月4日 上午10:32:36 
     * @param role
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addRole",method = {RequestMethod.POST},headers = {"content-type=application/json","content-type=application/xml"})
    @ResponseBody
    public SysRole addRole(@RequestBody SysRole role) throws Exception{
        roleService.save(role);
        return role;
    }
    /**
     * 
     * @Title: userAnthCheckRole 
     * @Description: 保持用户授权
     * @author dengbin
     * @date 2014年12月4日 上午10:32:39 
     * @param request
     * @param response
     */
    @RequestMapping(value="/roleAuthMenu")
    public void userAnthCheckRole(HttpServletRequest request,HttpServletResponse response){
        String roleId = request.getParameter("roleId");
        String checked = request.getParameter("checked");
        String indeterminate = request.getParameter("indeterminate");
        if(checked!=null&&!"".equals(checked))
            roleService.roleAnthMenu(roleId, checked.split(":"),indeterminate.split(":"));
        else
            roleService.roleAnthMenu(roleId, new String[0],new String[0]);
    }
    /**
     * 
     * @Title: deleteUser 
     * @Description: 删除角色
     * @author dengbin
     * @date 2014年12月4日 上午10:32:42 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/deleteRole")
    public @ResponseBody int deleteUser(HttpServletRequest request,HttpServletResponse response){
        String roleId=request.getParameter("roleId");
        if(roleId!=null&&!"".equals(roleId)){
            return roleService.deleteRole(roleId);
        }else{
            return 0;
        }
    }
}
