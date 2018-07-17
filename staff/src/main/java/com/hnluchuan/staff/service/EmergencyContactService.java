package com.hnluchuan.staff.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnluchuan.staff.dao.EmergencyContactDAO;
import com.hnluchuan.staff.model.EmergencyContact;
import com.hnluchuan.staff.dto.EmergencyContactDTO;
import com.hnluchuan.core.support.Page;
import com.hnluchuan.utils.common.BeanMapper;

@Service
public class EmergencyContactService extends BaseService {
	
	@Autowired
	private EmergencyContactDAO emergencyContactDAO;
	
	public List<EmergencyContactDTO> find(EmergencyContactDTO query, Page page) {
		List<EmergencyContact> models = emergencyContactDAO.find(query, page);
		List<EmergencyContactDTO> dtos = toDTOs(models);
		return dtos;
	}
	
	public List<EmergencyContactDTO> findAll() {
		return this.find(new EmergencyContactDTO(), null);
	}
	
	public List<EmergencyContactDTO> findAll(EmergencyContactDTO query) {
		return this.find(query, null);
	}
	
	public List<EmergencyContactDTO> find(Page page) {
		return this.find(new EmergencyContactDTO(), page);
	}
	
	public List<EmergencyContactDTO> find(int count) {
		Page page = new Page();
		page.setPage(1);
		page.setPageSize(count);
		return this.find(new EmergencyContactDTO(), page);
	}
	
	public Long create(EmergencyContactDTO dto) {
		EmergencyContact model = new EmergencyContact();
		toModel(model, dto);
		return emergencyContactDAO.create(model);
	}
	
	public EmergencyContactDTO load(Long id) {
	    EmergencyContact model = emergencyContactDAO.load(id);
	    return toDTO(model);
    }

	public void updateAllFields(EmergencyContactDTO dto) {
		EmergencyContact model = emergencyContactDAO.load(dto.getId());
		toModel(model, dto);
		emergencyContactDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		//throw new RuntimeException("默认禁止删除，请确认是物理删除还是逻辑删除");
		if (ids != null) {
			for (Long id : ids) {
				emergencyContactDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public EmergencyContactDTO findOne(EmergencyContactDTO query) {
		EmergencyContact model = emergencyContactDAO.findOne(query);
		return toDTO(model);
	}
	
	private List<EmergencyContactDTO> toDTOs(List<EmergencyContact> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<EmergencyContactDTO>(0);
		}
		List<EmergencyContactDTO> dtos = new ArrayList<EmergencyContactDTO>(models.size());
		for (EmergencyContact model : models) {
	        EmergencyContactDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
    
    public EmergencyContactDTO toDTO(EmergencyContact model) {
		if (model == null) {
			return null;
		}
		EmergencyContactDTO dto = BeanMapper.map(model, EmergencyContactDTO.class);
		
		return dto;
	}
	
	private void toModel(EmergencyContact model, EmergencyContactDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<EmergencyContact> toModels(List<EmergencyContactDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<EmergencyContact>(0);
		}
		List<EmergencyContact> models = new ArrayList<EmergencyContact>(dtos.size());
		for (EmergencyContactDTO dto : dtos) {
	        EmergencyContact model = new EmergencyContact();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}
}
