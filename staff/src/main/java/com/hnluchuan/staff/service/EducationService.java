package com.hnluchuan.staff.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnluchuan.staff.dao.EducationDAO;
import com.hnluchuan.staff.model.Education;
import com.hnluchuan.staff.dto.EducationDTO;
import com.hnluchuan.core.support.Page;
import com.hnluchuan.utils.common.BeanMapper;

@Service
public class EducationService extends BaseService {
	
	@Autowired
	private EducationDAO educationDAO;
	
	public List<EducationDTO> find(EducationDTO query, Page page) {
		List<Education> models = educationDAO.find(query, page);
		List<EducationDTO> dtos = toDTOs(models);
		return dtos;
	}
	
	public List<EducationDTO> findAll() {
		return this.find(new EducationDTO(), null);
	}
	
	public List<EducationDTO> findAll(EducationDTO query) {
		return this.find(query, null);
	}
	
	public List<EducationDTO> find(Page page) {
		return this.find(new EducationDTO(), page);
	}
	
	public List<EducationDTO> find(int count) {
		Page page = new Page();
		page.setPage(1);
		page.setPageSize(count);
		return this.find(new EducationDTO(), page);
	}
	
	public Long create(EducationDTO dto) {
		Education model = new Education();
		toModel(model, dto);
		return educationDAO.create(model);
	}
	
	public EducationDTO load(Long id) {
	    Education model = educationDAO.load(id);
	    return toDTO(model);
    }

	public void updateAllFields(EducationDTO dto) {
		Education model = educationDAO.load(dto.getId());
		toModel(model, dto);
		educationDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		//throw new RuntimeException("默认禁止删除，请确认是物理删除还是逻辑删除");
		if (ids != null) {
			for (Long id : ids) {
				educationDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public EducationDTO findOne(EducationDTO query) {
		Education model = educationDAO.findOne(query);
		return toDTO(model);
	}
	
	private List<EducationDTO> toDTOs(List<Education> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<EducationDTO>(0);
		}
		List<EducationDTO> dtos = new ArrayList<EducationDTO>(models.size());
		for (Education model : models) {
	        EducationDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
    
    public EducationDTO toDTO(Education model) {
		if (model == null) {
			return null;
		}
		EducationDTO dto = BeanMapper.map(model, EducationDTO.class);
		
		return dto;
	}
	
	private void toModel(Education model, EducationDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<Education> toModels(List<EducationDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<Education>(0);
		}
		List<Education> models = new ArrayList<Education>(dtos.size());
		for (EducationDTO dto : dtos) {
	        Education model = new Education();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}
}
