package com.hnluchuan.staff.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.hnluchuan.core.dao.BaseDAO;
import com.hnluchuan.core.support.Page;
import com.hnluchuan.staff.dto.ProvinceDTO;
import com.hnluchuan.staff.model.Province;

@Repository
public class ProvinceDAO extends BaseDAO<Province> {

    public List<Province> find(ProvinceDTO dto, Page page) {
        StringBuilder hql = new StringBuilder();
        List<Object> paras = new ArrayList<Object>();
        hql.append("From Province n where 1 = 1 ");
        if (dto.getId() != null) {
            hql.append(" and n.id = ? ");
            paras.add(dto.getId());
        }
        if (StringUtils.isNotBlank(dto.getName())) {
            hql.append(" and n.name like ? ");
            paras.add("%" + dto.getName() + "%");
        }					
        if (StringUtils.isNotBlank(dto.getLetter())) {
            hql.append(" and n.letter = ? ");
            paras.add(dto.getLetter());
        }		
        if (dto.getIsHot() != null) {
            hql.append(" and n.isHot = ? ");
            paras.add(dto.getIsHot());
        }
        if (StringUtils.isNotBlank(dto.getFullletter())) {
            hql.append(" and n.fullletter like ? ");
            paras.add("%" + dto.getFullletter() + "%");
        }					
        return super.find(hql.toString(), paras.toArray(), page);
    }
    
    public Province findOne(ProvinceDTO dto) {
		Page page = new Page();
		page.setPageSize(1);
		page.setPage(1);
		
		
		List<Province> list = this.find(dto, page);
	    return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }
}
