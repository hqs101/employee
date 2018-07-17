package com.hnluchuan.staff.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnluchuan.core.support.Page;
import com.hnluchuan.utils.common.BeanMapper;
import com.hnluchuan.staff.dao.ExampleDAO;
import com.hnluchuan.staff.dto.ExampleDTO;
import com.hnluchuan.staff.model.Example;

@Service
public class ExampleService {
	
	@Autowired
	private ExampleDAO exampleDAO;
	
	public List<ExampleDTO> find(ExampleDTO query, Page page) {
		List<Example> models = exampleDAO.find(query, page);
		List<ExampleDTO> dtos = toDTOs(models);
		return dtos;
	}
	
	public List<ExampleDTO> findAll() {
		return this.find(new ExampleDTO(), null);
	}
	
	public List<ExampleDTO> find(int count) {
		Page page = new Page();
		page.setPage(1);
		page.setPageSize(count);
		return this.find(new ExampleDTO(), page);
	}
	
	public Long create(ExampleDTO dto) {
		Example model = new Example();
		toModel(model, dto);
		return exampleDAO.create(model);
	}
	
	public ExampleDTO load(Long id) {
	    Example model = exampleDAO.load(id);
	    return toDTO(model);
    }

	public void updateAllFields(ExampleDTO dto) {
		Example model = exampleDAO.load(dto.getId());
		toModel(model, dto);
		exampleDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				exampleDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public ExampleDTO findOne(ExampleDTO query) {
		Example model = exampleDAO.findOne(query);
		return toDTO(model);
	}
	
	private List<ExampleDTO> toDTOs(List<Example> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<ExampleDTO>(0);
		}
		List<ExampleDTO> dtos = new ArrayList<ExampleDTO>(models.size());
		for (Example model : models) {
	        ExampleDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
    
    private ExampleDTO toDTO(Example model) {
		if (model == null) {
			return null;
		}
		ExampleDTO dto = BeanMapper.map(model, ExampleDTO.class);
		
		return dto;
	}
	
	private void toModel(Example model, ExampleDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<Example> toModels(List<ExampleDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<Example>(0);
		}
		List<Example> models = new ArrayList<Example>(dtos.size());
		for (ExampleDTO dto : dtos) {
	        Example model = new Example();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}
}
