 package com.hnluchuan.staff.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.hnluchuan.staff.dao.RoleDAO;
import com.hnluchuan.staff.dao.UserRoleDAO;
import com.hnluchuan.staff.model.Role;
import com.hnluchuan.staff.model.UserRole;

@Service
public class UserRoleService {

    @Autowired
    private UserRoleDAO userRoleDAO;
    
    @Autowired
    private RoleDAO roleDAO;
    
    public List<Role> findRolesByUserId(Long userId) {
        List<UserRole> userRoles = userRoleDAO.findByUserId(userId);
        List<Role> roles = Lists.newArrayList();
        for (UserRole userRole : userRoles) {
            roles.add(roleDAO.load(userRole.getRoleId()));
        }
        return roles;
    }
    
    public void save(Long[] ids, Long userId) {
    	if (ids != null && userId != null) {
    		userRoleDAO.deleteByUserId(userId);
			for (Long roleId : ids) {
				UserRole model = new UserRole();
				model.setRoleId(roleId);
				model.setUserId(userId);
				userRoleDAO.create(model);
			}
		}
    }
    
}
