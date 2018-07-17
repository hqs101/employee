package com.hnluchuan.staff.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnluchuan.staff.dao.FamilyDAO;
import com.hnluchuan.staff.model.Family;
import com.hnluchuan.staff.dto.FamilyDTO;
import com.hnluchuan.core.support.Page;
import com.hnluchuan.utils.common.BeanMapper;

@Service
public class FamilyService extends BaseService {
	
	@Autowired
	private FamilyDAO familyDAO;
	
	public List<FamilyDTO> find(FamilyDTO query, Page page) {
		List<Family> models = familyDAO.find(query, page);
		List<FamilyDTO> dtos = toDTOs(models);
		return dtos;
	}
	
	public List<FamilyDTO> findAll() {
		return this.find(new FamilyDTO(), null);
	}
	
	public List<FamilyDTO> findAll(FamilyDTO query) {
		return this.find(query, null);
	}
	
	public List<FamilyDTO> find(Page page) {
		return this.find(new FamilyDTO(), page);
	}
	
	public List<FamilyDTO> find(int count) {
		Page page = new Page();
		page.setPage(1);
		page.setPageSize(count);
		return this.find(new FamilyDTO(), page);
	}
	
	public Long create(FamilyDTO dto) {
		Family model = new Family();
		toModel(model, dto);
		return familyDAO.create(model);
	}
	
	public FamilyDTO load(Long id) {
	    Family model = familyDAO.load(id);
	    return toDTO(model);
    }

	public void updateAllFields(FamilyDTO dto) {
		Family model = familyDAO.load(dto.getId());
		toModel(model, dto);
		familyDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		//throw new RuntimeException("默认禁止删除，请确认是物理删除还是逻辑删除");
		if (ids != null) {
			for (Long id : ids) {
				familyDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public FamilyDTO findOne(FamilyDTO query) {
		Family model = familyDAO.findOne(query);
		return toDTO(model);
	}
	
	private List<FamilyDTO> toDTOs(List<Family> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<FamilyDTO>(0);
		}
		List<FamilyDTO> dtos = new ArrayList<FamilyDTO>(models.size());
		for (Family model : models) {
	        FamilyDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
    
    public FamilyDTO toDTO(Family model) {
		if (model == null) {
			return null;
		}
		FamilyDTO dto = BeanMapper.map(model, FamilyDTO.class);
		
		return dto;
	}
	
	private void toModel(Family model, FamilyDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<Family> toModels(List<FamilyDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<Family>(0);
		}
		List<Family> models = new ArrayList<Family>(dtos.size());
		for (FamilyDTO dto : dtos) {
	        Family model = new Family();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}
}
