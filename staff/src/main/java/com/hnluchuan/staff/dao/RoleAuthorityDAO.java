package com.hnluchuan.staff.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hnluchuan.core.dao.BaseDAO;
import com.hnluchuan.staff.model.RoleAuthority;

@Repository
public class RoleAuthorityDAO extends BaseDAO<RoleAuthority> {

    public List<RoleAuthority> findByRoleId(Long roleId) {
        String hql = "from RoleAuthority where roleId = ?";
        return find(hql, new Object[] {roleId}, null);
    }

    public void deleteByRoleId(Long roleId) {
        String hql = "delete from RoleAuthority where roleId = ?";
        super.executeUpdate(hql, new Object[] {roleId});
    }

}
