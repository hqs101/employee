package com.hnluchuan.staff.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnluchuan.core.support.Page;
import com.hnluchuan.utils.common.BeanMapper;
import com.hnluchuan.staff.dao.BizConfigDAO;
import com.hnluchuan.staff.dto.BizConfigDTO;
import com.hnluchuan.staff.model.BizConfig;

@Service
public class BizConfigService {
	
	@Autowired
	private BizConfigDAO bizConfigDAO;
	
	public List<BizConfigDTO> find(BizConfigDTO query, Page page) {
		List<BizConfig> models = bizConfigDAO.find(query, page);
		List<BizConfigDTO> dtos = toDTOs(models);
		return dtos;
	}
	
	public List<BizConfigDTO> findAll() {
		return this.find(new BizConfigDTO(), null);
	}
	
	public List<BizConfigDTO> find(int count) {
		Page page = new Page();
		page.setPage(1);
		page.setPageSize(count);
		return this.find(new BizConfigDTO(), page);
	}
	
	public Long create(BizConfigDTO dto) {
		BizConfig model = new BizConfig();
		toModel(model, dto);
		return bizConfigDAO.create(model);
	}
	
	public BizConfigDTO load(Long id) {
	    BizConfig model = bizConfigDAO.load(id);
	    return toDTO(model);
    }

	public void updateAllFields(BizConfigDTO dto) {
		BizConfig model = bizConfigDAO.load(dto.getId());
		toModel(model, dto);
		bizConfigDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				bizConfigDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public BizConfigDTO findOne(BizConfigDTO query) {
		BizConfig model = bizConfigDAO.findOne(query);
		return toDTO(model);
	}
	
	private List<BizConfigDTO> toDTOs(List<BizConfig> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<BizConfigDTO>(0);
		}
		List<BizConfigDTO> dtos = new ArrayList<BizConfigDTO>(models.size());
		for (BizConfig model : models) {
	        BizConfigDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
    
    private BizConfigDTO toDTO(BizConfig model) {
		if (model == null) {
			return null;
		}
		BizConfigDTO dto = BeanMapper.map(model, BizConfigDTO.class);
		
		return dto;
	}
	
	private void toModel(BizConfig model, BizConfigDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<BizConfig> toModels(List<BizConfigDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<BizConfig>(0);
		}
		List<BizConfig> models = new ArrayList<BizConfig>(dtos.size());
		for (BizConfigDTO dto : dtos) {
	        BizConfig model = new BizConfig();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}

	public String findValueByName(String name) {
		BizConfigDTO query = new BizConfigDTO();
		query.setName(name);
		BizConfig model = bizConfigDAO.findOne(query);
		if (model != null) {
			return model.getValue();
		} else {
			return null;
		}
	}
}
