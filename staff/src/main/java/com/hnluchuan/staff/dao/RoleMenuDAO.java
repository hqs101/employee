package com.hnluchuan.staff.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.hnluchuan.core.dao.BaseDAO;
import com.hnluchuan.core.support.Page;
import com.hnluchuan.staff.dto.RoleMenuDTO;
import com.hnluchuan.staff.model.RoleMenu;

@Repository
public class RoleMenuDAO extends BaseDAO<RoleMenu> {

    public List<RoleMenu> find(RoleMenuDTO dto, Page page) {
        StringBuilder hql = new StringBuilder();
        List<Object> paras = new ArrayList<Object>();
        hql.append("From RoleMenu n where 1 = 1 ");
        if (dto.getId() != null) {
            hql.append(" and n.id = ? ");
            paras.add(dto.getId());
        }
        if (dto.getRole() != null) {
        	if (dto.getRole().getId() != null) {
	            hql.append(" and n.role.id = ? ");
	            paras.add(dto.getRole().getId());
        	}
        }			
        hql.append(" order by n.id desc ");
        return super.find(hql.toString(), paras.toArray(), page);
    }
    
    public RoleMenu findOne(RoleMenuDTO dto) {
		Page page = new Page();
		page.setPageSize(1);
		page.setPage(1);
		
		List<RoleMenu> list = this.find(dto, page);
	    return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }
    
    public void deleteByRole(Long roleId) {
		super.executeUpdate("delete from RoleMenu where role.id = ?", new Object[] {roleId});
	}
}
