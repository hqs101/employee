package com.hnluchuan.staff.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.hnluchuan.core.dao.BaseDAO;
import com.hnluchuan.core.support.Page;
import com.hnluchuan.staff.dto.DistrictDTO;
import com.hnluchuan.staff.model.District;

@Repository
public class DistrictDAO extends BaseDAO<District> {

    public List<District> find(DistrictDTO dto, Page page) {
        StringBuilder hql = new StringBuilder();
        List<Object> paras = new ArrayList<Object>();
        hql.append("From District n where 1 = 1 ");
        if (dto.getId() != null) {
            hql.append(" and n.id = ? ");
            paras.add(dto.getId());
        }
        if (StringUtils.isNotBlank(dto.getName())) {
            hql.append(" and n.name like ? ");
            paras.add("%" + dto.getName() + "%");
        }					
        if (dto.getCity() != null) {
        	if (dto.getCity().getId() != null) {
	            hql.append(" and n.city.id = ? ");
	            paras.add(dto.getCity().getId());
        	}
        }			
        if (StringUtils.isNotBlank(dto.getLetter())) {
            hql.append(" and n.letter = ? ");
            paras.add(dto.getLetter());
        }		
        if (StringUtils.isNotBlank(dto.getFullLetter())) {
            hql.append(" and n.fullLetter like ? ");
            paras.add("%" + dto.getFullLetter() + "%");
        }
        return super.find(hql.toString(), paras.toArray(), page);
    }
    
    public District findOne(DistrictDTO dto) {
		Page page = new Page();
		page.setPageSize(1);
		page.setPage(1);
		
		List<District> list = this.find(dto, page);
	    return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }
}
