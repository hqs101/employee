package com.hnluchuan.staff.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hnluchuan.utils.exception.BizException;
import com.hnluchuan.staff.dao.UserDAO;
import com.hnluchuan.staff.model.Authority;
import com.hnluchuan.staff.model.Role;
import com.hnluchuan.staff.model.User;



@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService, Serializable {
	private static Logger logger = Logger.getLogger(MyUserDetailsService.class);
    private static final long serialVersionUID = 1L;
    
    public static String PREFIX = "ROLE_";

    @Autowired
    private UserDAO userDAO;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private RoleAuthorityService roleAuthorityService;
	@Autowired
	private RoleMenuService roleMenuService;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	try {
	        return doLoad(username);
        } catch (Exception e) {
        	logger.error(e, e);
        	throw new BizException(e.getMessage(), e);
        }
    }

	@SuppressWarnings("serial")
	private UserDetails doLoad(String username) {
	    User user = userDAO.loadUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Can't find user by username [" + username + "]");
        }
        
        // 最终用户拥有的所有权限
        List<GrantedAuthority> finalAuthorities = new ArrayList<GrantedAuthority>();
        
        // 找角色，然后找出每个角色拥有的权限
        List<Role> roles = userRoleService.findRolesByUserId(user.getId());
        for (Role role : roles) {
            List<Authority> authorities = roleAuthorityService.findByAuthoritiesByRoleId(role.getId());
            addToFinalAuthorities(finalAuthorities, authorities);
        }
        finalAuthorities.add(new GrantedAuthority() {
				@Override
				public String getAuthority() {
					return "ROLE_USER";
				}
        });
        finalAuthorities.add(new GrantedAuthority() {
				@Override
				public String getAuthority() {
					return "ROLE_user.add";
				}
			});
        //}
        user.setAuthorities(finalAuthorities);
        
        return user;
    }

    private void addToFinalAuthorities(List<GrantedAuthority> finalAuthorities, List<Authority> authorities) {
        for (Authority authority : authorities) {
            addToFinalAuthorities(finalAuthorities, authority);
        }
    }
    
    private void addToFinalAuthorities(List<GrantedAuthority> finalAuthorities, Authority authority) {
        final String code = authority.getCode();
        if (StringUtils.isNotBlank(code)) {
            finalAuthorities.add(new GrantedAuthority() {
                private static final long serialVersionUID = 1L;
                @Override
                public String getAuthority() {
                    return PREFIX + code;
                }
            });
        }
    }

}
