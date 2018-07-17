package com.hnluchuan.staff.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.hnluchuan.core.dao.BaseDAO;
import com.hnluchuan.core.support.Page;
import com.hnluchuan.utils.exception.BizException;
import com.hnluchuan.staff.dto.CityDTO;
import com.hnluchuan.staff.model.City;

@Repository
public class CityDAO extends BaseDAO<City> {

    public List<City> find(CityDTO dto, Page page) {
        StringBuilder hql = new StringBuilder();
        List<Object> paras = new ArrayList<Object>();
        hql.append("From City n where 1 = 1 ");
        if (dto.getId() != null) {
            hql.append(" and n.id = ? ");
            paras.add(dto.getId());
        }
        if (StringUtils.isNotBlank(dto.getName())) {
            hql.append(" and n.name like ? ");
            paras.add("%" + dto.getName() + "%");
        }					
        if (dto.getProvince() != null) {
        	if (dto.getProvince().getId() != null) {
	            hql.append(" and n.province.id = ? ");
	            paras.add(dto.getProvince().getId());
        	}
        }			
        if (dto.getIsHot() != null) {
            hql.append(" and n.isHot = ? ");
            paras.add(dto.getIsHot());
        }
        if (StringUtils.isNotBlank(dto.getFullletter())) {
            hql.append(" and n.fullletter like ? ");
            paras.add("%" + dto.getFullletter() + "%");
        }					
        if (StringUtils.isNotBlank(dto.getLetter())) {
            hql.append(" and n.letter = ? ");
            paras.add(dto.getLetter());
        }		
        return super.find(hql.toString(), paras.toArray(), page);
    }
    
    public City findOne(CityDTO dto) {
		Page page = new Page();
		page.setPageSize(1);
		page.setPage(1);
		
		List<City> list = this.find(dto, page);
	    return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }
    
    public List<City> getCitysFromUserType(int userType) {
    	StringBuffer sqlBuffer = new StringBuffer();
    	sqlBuffer.append("select distinct u.city from User u where type = ?");
    	return super.find(sqlBuffer.toString(), new Object[]{userType}, null);
    }

	public City findByName(String cityName) {
		throw new BizException("自己实现");
	}
}
