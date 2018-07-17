package com.hnluchuan.staff.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hnluchuan.core.dao.BaseDAO;
import com.hnluchuan.staff.model.UserRole;

@Repository
public class UserRoleDAO extends BaseDAO<UserRole> {

    public List<UserRole> findByUserId(Long userId) {
        String hql = "from UserRole where userId = ?";
        return super.find(hql, new Object[] {userId}, null);
    }

    public void deleteByUserId(Long userId) {
        String hql = "delete from UserRole where userId = ?";
        super.executeUpdate(hql, new Object[] {userId});
    }
}
