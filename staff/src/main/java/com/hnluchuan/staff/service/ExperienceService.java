package com.hnluchuan.staff.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnluchuan.staff.dao.ExperienceDAO;
import com.hnluchuan.staff.model.Experience;
import com.hnluchuan.staff.dto.ExperienceDTO;
import com.hnluchuan.core.support.Page;
import com.hnluchuan.utils.common.BeanMapper;

@Service
public class ExperienceService extends BaseService {
	
	@Autowired
	private ExperienceDAO experienceDAO;
	
	public List<ExperienceDTO> find(ExperienceDTO query, Page page) {
		List<Experience> models = experienceDAO.find(query, page);
		List<ExperienceDTO> dtos = toDTOs(models);
		return dtos;
	}
	
	public List<ExperienceDTO> findAll() {
		return this.find(new ExperienceDTO(), null);
	}
	
	public List<ExperienceDTO> findAll(ExperienceDTO query) {
		return this.find(query, null);
	}
	
	public List<ExperienceDTO> find(Page page) {
		return this.find(new ExperienceDTO(), page);
	}
	
	public List<ExperienceDTO> find(int count) {
		Page page = new Page();
		page.setPage(1);
		page.setPageSize(count);
		return this.find(new ExperienceDTO(), page);
	}
	
	public Long create(ExperienceDTO dto) {
		Experience model = new Experience();
		toModel(model, dto);
		return experienceDAO.create(model);
	}
	
	public ExperienceDTO load(Long id) {
	    Experience model = experienceDAO.load(id);
	    return toDTO(model);
    }

	public void updateAllFields(ExperienceDTO dto) {
		Experience model = experienceDAO.load(dto.getId());
		toModel(model, dto);
		experienceDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		//throw new RuntimeException("默认禁止删除，请确认是物理删除还是逻辑删除");
		if (ids != null) {
			for (Long id : ids) {
				experienceDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public ExperienceDTO findOne(ExperienceDTO query) {
		Experience model = experienceDAO.findOne(query);
		return toDTO(model);
	}
	
	private List<ExperienceDTO> toDTOs(List<Experience> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<ExperienceDTO>(0);
		}
		List<ExperienceDTO> dtos = new ArrayList<ExperienceDTO>(models.size());
		for (Experience model : models) {
	        ExperienceDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
    
    public ExperienceDTO toDTO(Experience model) {
		if (model == null) {
			return null;
		}
		ExperienceDTO dto = BeanMapper.map(model, ExperienceDTO.class);
		
		return dto;
	}
	
	private void toModel(Experience model, ExperienceDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<Experience> toModels(List<ExperienceDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<Experience>(0);
		}
		List<Experience> models = new ArrayList<Experience>(dtos.size());
		for (ExperienceDTO dto : dtos) {
	        Experience model = new Experience();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}
}
