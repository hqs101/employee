package com.hnluchuan.staff.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hnluchuan.core.dao.BaseDAO;
import com.hnluchuan.staff.model.UserAuthority;

@Repository
public class UserAuthorityDAO extends BaseDAO<UserAuthority> {

    public List<UserAuthority> findByUserId(Long userId) {
        String hql = "from UserAuthority where userId = ?";
        return find(hql, new Object[] {userId}, null);
    }

}
