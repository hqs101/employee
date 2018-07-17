package com.hnluchuan.staff.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnluchuan.core.support.Page;
import com.hnluchuan.utils.common.BeanMapper;
import com.hnluchuan.staff.dao.CityDAO;
import com.hnluchuan.staff.dto.CityDTO;
import com.hnluchuan.staff.model.City;

@Service
public class CityService {
	
	@Autowired
	private CityDAO cityDAO;
	
	public List<CityDTO> find(CityDTO query, Page page) {
		List<City> models = cityDAO.find(query, page);
		List<CityDTO> dtos = toDTOs(models);
		return dtos;
	}
	
	public List<CityDTO> getCitysFromUserType(int userType) {   //找到所有含有指定类型的城市（如运营商）
		List<City> cities = cityDAO.getCitysFromUserType(userType);
		List<CityDTO> dtos = new ArrayList<CityDTO>(cities.size());	
		for (City model : cities) {
	        CityDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
	}
	
	public List<CityDTO> findAll(CityDTO query) {
		return this.find(new CityDTO(), null);
	}
	
	public List<CityDTO> find(int count) {
		Page page = new Page();
		page.setPage(1);
		page.setPageSize(count);
		return this.find(new CityDTO(), page);
	}
	
	public Long create(CityDTO dto) {
		City model = new City();
		toModel(model, dto);
		return cityDAO.create(model);
	}
	
	public CityDTO load(Long id) {
	    City model = cityDAO.load(id);
	    return toDTO(model);
    }

	public void updateAllFields(CityDTO dto) {
		City model = cityDAO.load(dto.getId());
		toModel(model, dto);
		cityDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				cityDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public CityDTO findOne(CityDTO query) {
		City model = cityDAO.findOne(query);
		return toDTO(model);
	}
	
	private List<CityDTO> toDTOs(List<City> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<CityDTO>(0);
		}
		List<CityDTO> dtos = new ArrayList<CityDTO>(models.size());
		for (City model : models) {
	        CityDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
    
    private CityDTO toDTO(City model) {
		if (model == null) {
			return null;
		}
		CityDTO dto = BeanMapper.map(model, CityDTO.class);
		
		return dto;
	}
	
	private void toModel(City model, CityDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<City> toModels(List<CityDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<City>(0);
		}
		List<City> models = new ArrayList<City>(dtos.size());
		for (CityDTO dto : dtos) {
	        City model = new City();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}
	
	public Map<String, List<CityDTO>> ListByLetter() {
		Map<String, List<CityDTO>> map = new HashMap<String, List<CityDTO>>();
		List<CityDTO> cityList = this.find(new CityDTO(), null);
		for (CityDTO cityDTO : cityList) {
			List<CityDTO> list = map.get(cityDTO.getLetter());
			if (CollectionUtils.isNotEmpty(list)) {
				list.add(cityDTO);
			} else {
				List<CityDTO> temp = new ArrayList<CityDTO>();
				temp.add(cityDTO);
				map.put(cityDTO.getLetter(), temp);
			}
			
		}
		return map;
	}
}
