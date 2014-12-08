package com.digisky.listener;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.digisky.po.SysMenu;
import com.digisky.po.SysRole;
import com.digisky.po.SysUser;
import com.digisky.service.IMenuService;
import com.digisky.service.IRoleService;
import com.digisky.service.IUserService;

/**
 * 
 * @ClassName: MyListener 
 * @Description: 重新spring listeger，保存spring context并初始化数据
 * @author dengbin
 * @date 2014年12月4日 上午10:18:14
 */
public class MyListener extends ContextLoaderListener{

	private static ServletContext  context;


	public static ServletContext  getContext() {
		return context;
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		context = event.getServletContext();
		initData();
	}

	public static void setContext(ServletContext context) {
		MyListener.context = context;
	}
	/**
	 * 
	 * @Title: initData 
	 * @Description: 初始化系统数据
	 * @author dengbin
	 * @date 2014年12月4日 上午10:19:43
	 */
    public static void initData() {
        ApplicationContext bean = WebApplicationContextUtils
                .getRequiredWebApplicationContext(context);
        IUserService userService = (IUserService) bean.getBean("userService");
        long count = userService.getAllCount();
        if (count > 0)
            return;
        System.out.println("系统初始数据开始....");
        IMenuService menuService = (IMenuService) bean.getBean("menuService");
        List<SysMenu> menuList = new ArrayList<SysMenu>();
        menuList.add(new SysMenu("首页", "/index.html", 1));
        menuService.init(menuList);

        IRoleService roleService = (IRoleService) bean.getBean("roleService");
        SysRole role = new SysRole();
        role.setName("系统管理员");
        role.setStatus(1);
        role.setRemark("超级用户");
        roleService.save(role);

        String[] menuIds = { menuList.get(0).getId()};
        roleService.roleAnthMenu(role.getId(), menuIds,new String[0]);

        SysUser user = new SysUser();
        user.setName("admin");
        user.setPassword("123456");
        user.setAge(30);
        user.setIsAdmin(1);
        user.setSex(1);
        user.setStatus(1);
        user.setNote("系统预设管理员");
        userService.init(user);

        String[] roleIds = { role.getId() };
        userService.userAnthRole(user.getId(), roleIds);
    }

}
