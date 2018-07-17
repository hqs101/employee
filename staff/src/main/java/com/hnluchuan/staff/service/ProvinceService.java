package com.hnluchuan.staff.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnluchuan.core.support.Page;
import com.hnluchuan.utils.common.BeanMapper;
import com.hnluchuan.staff.dao.ProvinceDAO;
import com.hnluchuan.staff.dto.ProvinceDTO;
import com.hnluchuan.staff.model.Province;


@Service
public class ProvinceService {
	
	@Autowired
	private ProvinceDAO provinceDAO;
	
	public List<ProvinceDTO> find(ProvinceDTO query, Page page) {
		List<Province> models = provinceDAO.find(query, page);
		List<ProvinceDTO> dtos = toDTOs(models);
		return dtos;
	}
	
	public List<ProvinceDTO> findAll() {
		return this.find(new ProvinceDTO(), null);
	}
	
	public List<ProvinceDTO> find(int count) {
		Page page = new Page();
		page.setPage(1);
		page.setPageSize(count);
		return this.find(new ProvinceDTO(), page);
	}
	
	public Long create(ProvinceDTO dto) {
		Province model = new Province();
		toModel(model, dto);
		return provinceDAO.create(model);
	}
	
	public ProvinceDTO load(Long id) {
	    Province model = provinceDAO.load(id);
	    return toDTO(model);
    }

	public void updateAllFields(ProvinceDTO dto) {
		Province model = provinceDAO.load(dto.getId());
		toModel(model, dto);
		provinceDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				provinceDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public ProvinceDTO findOne(ProvinceDTO query) {
		Province model = provinceDAO.findOne(query);
		return toDTO(model);
	}
	
	private List<ProvinceDTO> toDTOs(List<Province> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<ProvinceDTO>(0);
		}
		List<ProvinceDTO> dtos = new ArrayList<ProvinceDTO>(models.size());
		for (Province model : models) {
	        ProvinceDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
    
    private ProvinceDTO toDTO(Province model) {
		if (model == null) {
			return null;
		}
		ProvinceDTO dto = BeanMapper.map(model, ProvinceDTO.class);
		
		return dto;
	}
	
	private void toModel(Province model, ProvinceDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<Province> toModels(List<ProvinceDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<Province>(0);
		}
		List<Province> models = new ArrayList<Province>(dtos.size());
		for (ProvinceDTO dto : dtos) {
	        Province model = new Province();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}
}
