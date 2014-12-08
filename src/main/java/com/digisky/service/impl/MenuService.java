package com.digisky.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.digisky.dao.IMenuDAO;
import com.digisky.dao.IRoleDAO;
import com.digisky.dto.EasyuiTreeDTO;
import com.digisky.po.RRoleMenu;
import com.digisky.po.SysMenu;
import com.digisky.po.SysRole;
import com.digisky.security.MyInvocationSecurityMetadataSource;
import com.digisky.service.IMenuService;

@Service
@Transactional
public class MenuService implements IMenuService{

    @Autowired
    private IMenuDAO menuDAO;
    
    @Autowired
    private IRoleDAO roleDAO;
    
    @Override
    @Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
    public List<SysMenu> queryAll() {
        return menuDAO.queryAll();
    }

    @Override
    @Transactional
    public void create(SysMenu menu) {
       if(menu.getId()==null||"".equals(menu.getId())){
           menu.setId(null);
           int sort = 0;
           try{
        	   sort = menuDAO.getMaxSort(menu.getParentId());
           }catch(Exception e){}
           menu.setSort(sort+1);
           menu.setLeaf(false);
           menu.setState("closed");
           if(menu.getUrl()!=null&&!"".equals(menu.getUrl())){
        	   if(menu.getUrl().lastIndexOf(".do")>0){
        		   menu.setLeaf(true);
        		   menu.setState("opened");
        	   }
           }
           menuDAO.create(menu);
           
           List<RRoleMenu> list = menuDAO.getByMenuId(menu.getParentId());
           if(list!=null&&list.size()>0){
        	   for(RRoleMenu r : list){
        		   RRoleMenu n = new RRoleMenu();
        		   n.setRoleId(r.getRoleId());
        		   n.setMenuId(menu.getId());
        		   roleDAO.saveRoleAnth(n);
        	   }
           }
           //重新加载
           initSecurity();
           
       }else{
    	   SysMenu old = menuDAO.findById(menu.getId());
    	   old.setText(menu.getText());
    	   old.setIconCls(menu.getIconCls());
    	   old.setUrl(menu.getUrl());
    	   old.setStatus(menu.getStatus());
           menuDAO.updateMenu(old);
       }
        
    }

    @Override
    @Transactional
    public int deleteMenu(String menuId) {
    	List<SysMenu> child = menuDAO.getByParentId(menuId);
    	if(child!=null&&child.size()>0){
    		return 0;
    	}
        menuDAO.clearMenuR(menuId);
        int res = menuDAO.deleteMenu(menuId);
        //重新加载
        initSecurity();
        return res;
    }

    @Override
    @Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
    public Set<EasyuiTreeDTO> queryByUser(String username) {
        List<SysMenu> list = menuDAO.queryByUser(username);
    	Map<String,Set<SysMenu>> map = new HashMap<String,Set<SysMenu>>();
    	for(SysMenu menu : list){
    		if(map.containsKey(menu.getParentId())){
    			map.get(menu.getParentId()).add(menu);
    		}else{
    			Set<SysMenu> set = new HashSet<SysMenu>();
    			set.add(menu);
    			map.put(menu.getParentId(), set);
    		}
    	}
    	Set<EasyuiTreeDTO> result = new TreeSet<EasyuiTreeDTO>(new Comparator<EasyuiTreeDTO>(){
			@Override
			public int compare(EasyuiTreeDTO o1, EasyuiTreeDTO o2) {
				return o1.getSort()-o2.getSort();
			}
    		
    	});
    	createTree(null,map,result);
    	return result;
    }

    @Override
    @Transactional
    public void init(List<SysMenu> list) {
        for(SysMenu menu : list){
            menuDAO.create(menu);
        }
    }
    
    
    @Override
    @Transactional(readOnly=true)
    public List<SysMenu> getAsyncMenuTree(String parentId){
    	return menuDAO.getByParentId(parentId);
    }
    
    @Override
    @Transactional(readOnly=true)
    public Set<EasyuiTreeDTO> getMenuTree(){
    	List<SysMenu> list = menuDAO.queryNotLeaf();
    	Map<String,Set<SysMenu>> map = new HashMap<String,Set<SysMenu>>();
    	for(SysMenu menu : list){
    		if(map.containsKey(menu.getParentId())){
    			map.get(menu.getParentId()).add(menu);
    		}else{
    			Set<SysMenu> set = new HashSet<SysMenu>();
    			set.add(menu);
    			map.put(menu.getParentId(), set);
    		}
    	}
    	Set<EasyuiTreeDTO> result = new TreeSet<EasyuiTreeDTO>(new Comparator<EasyuiTreeDTO>(){
			@Override
			public int compare(EasyuiTreeDTO o1, EasyuiTreeDTO o2) {
				return o1.getSort()-o2.getSort();
			}
    		
    	});
    	createTree(null,map,result);
    	return result;
    }
    
    
    
    private void createTree(String parentId,Map<String,Set<SysMenu>> map,Set<EasyuiTreeDTO> result){
		if(map.containsKey(parentId)){
			Set<SysMenu> list = map.get(parentId);
			for(SysMenu url : list){
				EasyuiTreeDTO dto = new EasyuiTreeDTO();
				dto.setId(url.getId());
				dto.setText(url.getText());
				dto.setChecked(true);
				dto.setLeaf(false);
				dto.setState("closed");
				dto.setUrl(url.getUrl());
				dto.setIconCls(url.getIconCls());
				dto.setParentId(url.getParentId());
				dto.setSort(url.getSort());
				dto.setChildren(new TreeSet<EasyuiTreeDTO>(new Comparator<EasyuiTreeDTO>(){
					@Override
					public int compare(EasyuiTreeDTO o1, EasyuiTreeDTO o2) {
						return o1.getSort()-o2.getSort();
					}
		    		
		    	}));
	            createTree(url.getId(),map,dto.getChildren());
	            result.add(dto);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.digisky.service.IMenuService#findById(java.lang.String) 
	 */
	@Override
	@Transactional(readOnly=true)
	public SysMenu findById(String id) {
		return menuDAO.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.digisky.service.IMenuService#getAuthMenuTree(java.lang.String) 
	 */
	@Override
	public Set<EasyuiTreeDTO> getAuthMenuTree(String roleId) {
		List<SysMenu> all = menuDAO.queryAll();
		List<SysMenu> auth = menuDAO.queryAnth(roleId);
		Map<String,Integer> authMap = new HashMap<String,Integer>();
		for(SysMenu m : auth){
			authMap.put(m.getId(), 0);
		}

		//
    	Map<String,Set<SysMenu>> map = new HashMap<String,Set<SysMenu>>();
    	for(SysMenu menu : all){
    		if(map.containsKey(menu.getParentId())){
    			map.get(menu.getParentId()).add(menu);
    		}else{
    			Set<SysMenu> set = new HashSet<SysMenu>();
    			set.add(menu);
    			map.put(menu.getParentId(), set);
    		}
    	}
    	Set<EasyuiTreeDTO> result = new TreeSet<EasyuiTreeDTO>(new Comparator<EasyuiTreeDTO>(){
			@Override
			public int compare(EasyuiTreeDTO o1, EasyuiTreeDTO o2) {
				return o1.getSort()-o2.getSort();
			}
    		
    	});
    	createTree(null,map,result,authMap);
    	return result;
	}
	
	
    private void createTree(String parentId,Map<String,Set<SysMenu>> map,Set<EasyuiTreeDTO> result,Map<String,Integer> authMap){
		if(map.containsKey(parentId)){
			Set<SysMenu> list = map.get(parentId);
			for(SysMenu url : list){
				EasyuiTreeDTO dto = new EasyuiTreeDTO();
				dto.setId(url.getId());
				dto.setText(url.getText());
				if(authMap.containsKey(url.getId())){
					dto.setChecked(true);
				}else{
					dto.setChecked(false);
				}
				dto.setLeaf(url.isLeaf());
				dto.setState("open");
				dto.setUrl(url.getUrl());
				dto.setIconCls(url.getIconCls());
				dto.setParentId(url.getParentId());
				dto.setSort(url.getSort());
				dto.setChildren(new TreeSet<EasyuiTreeDTO>(new Comparator<EasyuiTreeDTO>(){
					@Override
					public int compare(EasyuiTreeDTO o1, EasyuiTreeDTO o2) {
						return o1.getSort()-o2.getSort();
					}
		    		
		    	}));
	            createTree(url.getId(),map,dto.getChildren(),authMap);
	            result.add(dto);
			}
		}
	}
    
    
    public void initSecurity(){
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
