package com.hnluchuan.staff.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.hnluchuan.staff.dao.AuthorityDAO;
import com.hnluchuan.staff.dao.RoleAuthorityDAO;
import com.hnluchuan.staff.model.Authority;
import com.hnluchuan.staff.model.RoleAuthority;

@Service
public class RoleAuthorityService {

    @Autowired
    private RoleAuthorityDAO roleAuthorityDAO;
    @Autowired
    private AuthorityDAO authorityDAO;
    
    public List<Authority> findByAuthoritiesByRoleId(Long roleId) {
        List<RoleAuthority> roleAuthorities = roleAuthorityDAO.findByRoleId(roleId);
        List<Authority> authorities = Lists.newArrayList();
        for (RoleAuthority roleAuthority : roleAuthorities) {
            authorities.add(authorityDAO.load(roleAuthority.getAuthorityId()));
        }
        return authorities;
    }

    @Transactional
    public void update(Long roleId, Long[] authorityIds) {
        roleAuthorityDAO.deleteByRoleId(roleId);

        for (Long id : authorityIds) {
            RoleAuthority ra = new RoleAuthority();
            ra.setRoleId(roleId);
            ra.setAuthorityId(id);
            roleAuthorityDAO.create(ra);
        }
    }
    
}
