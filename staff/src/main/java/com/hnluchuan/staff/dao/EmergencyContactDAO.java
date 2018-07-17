package com.hnluchuan.staff.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;
	
import com.hnluchuan.core.dao.BaseDAO;
import com.hnluchuan.staff.model.EmergencyContact;
import com.hnluchuan.staff.dto.EmergencyContactDTO;
import com.hnluchuan.core.support.Page;

@Repository
public class EmergencyContactDAO extends BaseDAO<EmergencyContact> {

    public List<EmergencyContact> find(EmergencyContactDTO dto, Page page) {
        StringBuilder hql = new StringBuilder();
        List<Object> paras = new ArrayList<Object>();
        hql.append("From EmergencyContact n where 1 = 1 ");
        if (dto.getId() != null) {
            hql.append(" and n.id = ? ");
            paras.add(dto.getId());
        }
        if (dto.getEmployee() != null) {
        	if (dto.getEmployee().getId() != null) {
	            hql.append(" and n.employee.id = ? ");
	            paras.add(dto.getEmployee().getId());
        	}
        }			
        hql.append(" order by n.id desc ");
        return super.find(hql.toString(), paras.toArray(), page);
    }
    
    public EmergencyContact findOne(EmergencyContactDTO dto) {
		Page page = new Page();
		page.setPageSize(1);
		page.setPage(1);
		
		List<EmergencyContact> list = this.find(dto, page);
	    return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }
    
    @Override
	public Long create(EmergencyContact emergencyContact) {
		return super.create(emergencyContact);
	}

	@Override
	public void update(EmergencyContact emergencyContact) {
		super.update(emergencyContact);
	}
    
}
