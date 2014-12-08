package com.digisky.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.digisky.dao.IRoleDAO;
import com.digisky.po.RRoleMenu;
import com.digisky.po.SysMenu;
import com.digisky.po.SysRole;
import com.digisky.security.MyInvocationSecurityMetadataSource;
import com.digisky.service.IRoleService;

@Service
@Transactional
public class RoleService implements IRoleService{

    @Autowired
    private IRoleDAO roleDAO;
    
    @Override
    @Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
    public List<SysRole> queryAll() {
        return roleDAO.queryAll();
    }

    @Override
    @Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
    public List<SysRole> queryAnth(String userId) {
        return roleDAO.queryAnth(userId);
    }

    @Override
    @Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
    public List<SysRole> queryUnAnth(String userId) {
        return roleDAO.queryUnAnth(userId);
    }

    @Override
    @Transactional
    public void save(SysRole role) {
        if(role.getId()==null||"".equals(role.getId())){
            role.setId(null);
            roleDAO.save(role);
        }else{
            roleDAO.updateRole(role);
        }
        
    }

    @Override
    @Transactional
    public void roleAnthMenu(String roleId, String[] checked,String[] indeterminate) {
        roleDAO.delRoleAnth(roleId);
        for(String menuId : checked){
            RRoleMenu rrm = new RRoleMenu();
            rrm.setRoleId(roleId);
            rrm.setMenuId(menuId);
            rrm.setState(1);
            roleDAO.saveRoleAnth(rrm);
        }
        
        for(String menuId : indeterminate){
            RRoleMenu rrm = new RRoleMenu();
            rrm.setRoleId(roleId);
            rrm.setMenuId(menuId);
            rrm.setState(0);
            roleDAO.saveRoleAnth(rrm);
        }
        
        //重新加载security
        initSecurity();
    }

    @Override
    @Transactional
    public int deleteRole(String roleId) {
        roleDAO.deluserAnth(roleId);
        roleDAO.delRoleAnth(roleId);
        int res = roleDAO.deleteRole(roleId);
        //重新加载
        initSecurity();
        return res;
    }
    
    /**
     * 
     * @Title: initSecurity 
     * @Description: 修改了角色后需要重新初始化权限验证
     * @author dengbin
     * @date 2014年12月3日 下午6:25:43
     */
    private void initSecurity(){
        MyInvocationSecurityMetadataSource.getResourceMap().clear();
        List<Object[]> list = roleDAO.querySecurity();
        for(Object[] objs : list){
            Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
            SysRole role = (SysRole) objs[0];
            SysMenu menu = (SysMenu) objs[1];
            if(MyInvocationSecurityMetadataSource.getResourceMap().get(menu.getUrl())!=null&&
                    !"".equals(MyInvocationSecurityMetadataSource.getResourceMap().get(menu.getUrl()))){
                ConfigAttribute ca = new SecurityConfig(role.getName());
                MyInvocationSecurityMetadataSource.getResourceMap().get(menu.getUrl()).add(ca);
            }else{
                ConfigAttribute ca = new SecurityConfig(role.getName());
                atts.add(ca);
                MyInvocationSecurityMetadataSource.getResourceMap().put(menu.getUrl(), atts);
            }
        }
    }

}
