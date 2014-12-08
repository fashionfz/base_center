package com.digisky.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.digisky.common.Constant;
import com.digisky.dao.IUserDAO;
import com.digisky.po.RUserRole;
import com.digisky.po.SysUser;
import com.digisky.service.IUserService;

@Service
@Transactional
public class UserService implements IUserService{

    @Autowired
    private IUserDAO userDAO;
    
    
     private Md5PasswordEncoder encoder;
     
    @Override
    @Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
    public List<SysUser> queryAll(int page,int rows) {
        return userDAO.queryAll(page,rows);
    }

    @Override
    @Transactional
    public int create(SysUser user) {
        encoder = new Md5PasswordEncoder();
        if(user.getId()==null||"".equals(user.getId())){
            user.setId(null);
            user.setPassword(encoder.encodePassword(user.getPassword(), user.getName()));
            userDAO.create(user);
        }
        else{
            SysUser old = userDAO.findById(user.getId());
            if(!old.getPassword().equals(user.getPassword()))
                user.setPassword(encoder.encodePassword(user.getPassword(), user.getName()));
            userDAO.updateUser(user);
        }
        return Constant.REQUEST_SUCCESS;
    }

    @Override
    @Transactional
    public long getAllCount() {
        return userDAO.getAllCount();
    }

    @Override
    @Transactional
    public void userAnthRole(String userId, String[] roleIds) {
        userDAO.delUserAnth(userId);
        for(String roleId : roleIds){
            RUserRole rur = new RUserRole();
            rur.setRoleId(roleId);
            rur.setUserId(userId);
            userDAO.saveUserAnth(rur);
        }
        
    }

    @Override
    @Transactional
    public int deleteUser(String userId) {
        userDAO.delUserAnth(userId);
        return userDAO.deleteUser(userId);
    }

    @Override
    @Transactional
    public void init(SysUser user) {
        encoder = new Md5PasswordEncoder();
        user.setPassword(encoder.encodePassword(user.getPassword(), user.getName()));
        userDAO.create(user);
    }

}
