package com.hnluchuan.staff.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnluchuan.core.support.Page;
import com.hnluchuan.utils.common.BeanMapper;
import com.hnluchuan.staff.dao.DistrictDAO;
import com.hnluchuan.staff.dto.DistrictDTO;
import com.hnluchuan.staff.model.District;

@Service
public class DistrictService {
	
	@Autowired
	private DistrictDAO districtDAO;
	
	public List<DistrictDTO> find(DistrictDTO query, Page page) {
		List<District> models = districtDAO.find(query, page);
		List<DistrictDTO> dtos = toDTOs(models);
		return dtos;
	}
	
	public List<DistrictDTO> findAll() {
		return this.find(new DistrictDTO(), null);
	}
	
	public List<DistrictDTO> find(int count) {
		Page page = new Page();
		page.setPage(1);
		page.setPageSize(count);
		return this.find(new DistrictDTO(), page);
	}
	
	public Long create(DistrictDTO dto) {
		District model = new District();
		toModel(model, dto);
		return districtDAO.create(model);
	}
	
	public DistrictDTO load(Long id) {
	    District model = districtDAO.load(id);
	    return toDTO(model);
    }

	public void updateAllFields(DistrictDTO dto) {
		District model = districtDAO.load(dto.getId());
		toModel(model, dto);
		districtDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				districtDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public DistrictDTO findOne(DistrictDTO query) {
		District model = districtDAO.findOne(query);
		return toDTO(model);
	}
	
	private List<DistrictDTO> toDTOs(List<District> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<DistrictDTO>(0);
		}
		List<DistrictDTO> dtos = new ArrayList<DistrictDTO>(models.size());
		for (District model : models) {
	        DistrictDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
    
    private DistrictDTO toDTO(District model) {
		if (model == null) {
			return null;
		}
		DistrictDTO dto = BeanMapper.map(model, DistrictDTO.class);
		
		return dto;
	}
	
	private void toModel(District model, DistrictDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<District> toModels(List<DistrictDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<District>(0);
		}
		List<District> models = new ArrayList<District>(dtos.size());
		for (DistrictDTO dto : dtos) {
	        District model = new District();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}
}
