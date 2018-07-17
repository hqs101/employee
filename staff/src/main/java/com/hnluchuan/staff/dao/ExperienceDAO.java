package com.hnluchuan.staff.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;
	
import com.hnluchuan.core.dao.BaseDAO;
import com.hnluchuan.staff.model.Experience;
import com.hnluchuan.staff.dto.ExperienceDTO;
import com.hnluchuan.core.support.Page;

@Repository
public class ExperienceDAO extends BaseDAO<Experience> {

    public List<Experience> find(ExperienceDTO dto, Page page) {
        StringBuilder hql = new StringBuilder();
        List<Object> paras = new ArrayList<Object>();
        hql.append("From Experience n where 1 = 1 ");
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
    
    public Experience findOne(ExperienceDTO dto) {
		Page page = new Page();
		page.setPageSize(1);
		page.setPage(1);
		
		List<Experience> list = this.find(dto, page);
	    return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }
    
    @Override
	public Long create(Experience experience) {
		return super.create(experience);
	}

	@Override
	public void update(Experience experience) {
		super.update(experience);
	}
    
}
