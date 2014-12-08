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

import com.digisky.dto.QueryUserDTO;
import com.digisky.po.SysUser;
import com.digisky.service.IUserService;
/**
 * 
 * @ClassName: UserController 
 * @Description: 用户处理action
 * @author dengbin
 * @date 2014年12月4日 上午10:34:52
 */
@Controller
public class UserController {

    @Autowired
    private IUserService userService;
    
    /**
     * 
     * @Title: create 
     * @Description: 新增、修改用户
     * @author dengbin
     * @date 2014年12月4日 上午10:35:02 
     * @param user
     * @return
     * @throws Exception
     */
	@RequestMapping(value = "/addUser",method = {RequestMethod.POST},headers = {"content-type=application/json","content-type=application/xml"})
	@ResponseBody
	public int create(@RequestBody SysUser user) throws Exception{
        return userService.create(user);
	}
	/**
	 * 
	 * @Title: queryUser 
	 * @Description: 查询用户
	 * @author dengbin
	 * @date 2014年12月4日 上午10:35:04 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/queryUser")
	public @ResponseBody QueryUserDTO queryUser(HttpServletRequest request,HttpServletResponse response){
	    String page = request.getParameter("page");
	    String rows = request.getParameter("rows");
	    QueryUserDTO dto = new QueryUserDTO();
	    long count = userService.getAllCount();
	    List<SysUser> list = userService.queryAll(Integer.parseInt(page),Integer.parseInt(rows));
	    dto.setTotal(count);
	    dto.setRows(list);
	    return dto;
	}
	/**
	 * 
	 * @Title: userAnthRole 
	 * @Description: 用户授权保持
	 * @author dengbin
	 * @date 2014年12月4日 上午10:35:07 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/userAuthRole")
	public void userAnthRole(HttpServletRequest request,HttpServletResponse response){
	    String userId = request.getParameter("userId");
	    String roleIds = request.getParameter("roleIds");
	    if(roleIds!=null&&!"".equals(roleIds))
	        userService.userAnthRole(userId, roleIds.split(":"));
	    else
	        userService.userAnthRole(userId, new String[0]);
	}
	/**
	 * 
	 * @Title: deleteUser 
	 * @Description: 删除用户
	 * @author dengbin
	 * @date 2014年12月4日 上午10:35:10 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/deleteUser")
	public @ResponseBody int deleteUser(HttpServletRequest request,HttpServletResponse response){
	    String userId=request.getParameter("userId");
	    if(userId!=null&&!"".equals(userId)){
	        return userService.deleteUser(userId);
	    }else{
	        return 0;
	    }
	}

}
