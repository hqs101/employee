package com.hnluchuan.staff.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.hnluchuan.staff.dao.AuthorityDAO;
import com.hnluchuan.staff.dao.UserAuthorityDAO;
import com.hnluchuan.staff.model.Authority;
import com.hnluchuan.staff.model.UserAuthority;

@Service
public class UserAuthorityService {
    @Autowired
    private UserAuthorityDAO userAuthorityDAO;
    
    @Autowired
    private AuthorityDAO authorityDAO;
    
    public List<Authority> findAuthoritiesByUserId(Long userId) {
        List<UserAuthority> userAuthorities = userAuthorityDAO.findByUserId(userId);
        List<Authority> authorities = Lists.newArrayList();
        for (UserAuthority userAuthority : userAuthorities) {
            authorities.add(authorityDAO.load(userAuthority.getAuthorityId()));
        }
        return authorities;
    }
}
