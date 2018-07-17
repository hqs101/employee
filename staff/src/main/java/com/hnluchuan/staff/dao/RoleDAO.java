package com.hnluchuan.staff.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.hnluchuan.core.dao.BaseDAO;
import com.hnluchuan.core.support.Page;
import com.hnluchuan.staff.dto.RoleDTO;
import com.hnluchuan.staff.model.Role;

/**
 * dao的实现类必须加上@Repository
 * 继承自GenericDAOHibernate，以重用基本的增删改方法。
 * @author Kewen.Zhu
 *
 */
@Repository
public class RoleDAO extends BaseDAO<Role> {

    /**
     * 查询方法，传入一个dto是为了灵活构建查询条件，避免创建多个查询方法。
     * page对象是用来分页的，一般可以不用管
     * @param dto
     * @param page
     * @return
     */
    public List<Role> find(RoleDTO dto, Page page) {
        StringBuilder hql = new StringBuilder();
        List<Object> paras = new ArrayList<Object>();
        hql.append("From Role n where 1 = 1 ");
        if (dto.getId() != null) {
            hql.append(" and n.id = ? ");
            paras.add(dto.getId());
        }
        if (StringUtils.isNotBlank(dto.getName())) {
        	hql.append(" and n.name = ? ");
        	paras.add(dto.getName());
        }
        if (StringUtils.isNotBlank(dto.getCode())) {
        	hql.append(" and n.code = ? ");
        	paras.add(dto.getCode());
        }
        return super.find(hql.toString(), paras.toArray(), page);
    }
    
    public Role uniqueFind(RoleDTO dto) {
		Page page = new Page();
		page.setPageSize(1);
		page.setPage(1);
		
		List<Role> list = this.find(dto, page);
	    return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }
}
