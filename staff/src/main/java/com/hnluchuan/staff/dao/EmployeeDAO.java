package com.hnluchuan.staff.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang3.StringUtils;
	
import com.hnluchuan.core.dao.BaseDAO;
import com.hnluchuan.staff.model.Employee;
import com.hnluchuan.staff.dto.EmployeeDTO;
import com.hnluchuan.core.support.Page;

@Repository
public class EmployeeDAO extends BaseDAO<Employee> {

    public List<Employee> find(EmployeeDTO dto, Page page) {
        StringBuilder hql = new StringBuilder();
        List<Object> paras = new ArrayList<Object>();
        hql.append("From Employee n where 1 = 1 ");
        if (dto.getId() != null) {
            hql.append(" and n.id = ? ");
            paras.add(dto.getId());
        }
        if (dto.getUser() != null) {
        	if (dto.getUser().getId() != null) {
	            hql.append(" and n.user.id = ? ");
	            paras.add(dto.getUser().getId());
        	}
        }			
        if (StringUtils.isNotBlank(dto.getName())) {
            hql.append(" and n.name like ? ");
            paras.add("%" + dto.getName() + "%");
        }					
        if (StringUtils.isNotBlank(dto.getPhone())) {
            hql.append(" and n.phone = ? ");
            paras.add(dto.getPhone());
        }		
        if (dto.getStatus() != null) {
            hql.append(" and n.status = ? ");
            paras.add(dto.getStatus());
        }
        hql.append(" order by n.id desc ");
        return super.find(hql.toString(), paras.toArray(), page);
    }
    
    public Employee findOne(EmployeeDTO dto) {
		Page page = new Page();
		page.setPageSize(1);
		page.setPage(1);
		
		List<Employee> list = this.find(dto, page);
	    return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }
    
    @Override
	public Long create(Employee employee) {
		return super.create(employee);
	}

	@Override
	public void update(Employee employee) {
		super.update(employee);
	}
    
}
