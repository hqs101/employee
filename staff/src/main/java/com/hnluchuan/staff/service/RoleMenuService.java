package com.hnluchuan.staff.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hnluchuan.core.support.Page;
import com.hnluchuan.utils.common.BeanMapper;
import com.hnluchuan.staff.dao.MenuDAO;
import com.hnluchuan.staff.dao.RoleMenuDAO;
import com.hnluchuan.staff.dto.RoleMenuDTO;
import com.hnluchuan.staff.model.Menu;
import com.hnluchuan.staff.model.Role;
import com.hnluchuan.staff.model.RoleMenu;

@Service
public class RoleMenuService {
	
	@Autowired
	private RoleMenuDAO roleMenuDAO;
	@Autowired
	private MenuDAO menuDAO;
	
	public List<RoleMenuDTO> find(RoleMenuDTO query, Page page) {
		List<RoleMenu> models = roleMenuDAO.find(query, page);
		List<RoleMenuDTO> dtos = toDTOs(models);
		return dtos;
	}
	
	public List<RoleMenuDTO> findAll() {
		return this.find(new RoleMenuDTO(), null);
	}
	
	public List<RoleMenuDTO> find(int count) {
		Page page = new Page();
		page.setPage(1);
		page.setPageSize(count);
		return this.find(new RoleMenuDTO(), page);
	}
	
	public Long create(RoleMenuDTO dto) {
		RoleMenu model = new RoleMenu();
		toModel(model, dto);
		return roleMenuDAO.create(model);
	}
	
	public RoleMenuDTO load(Long id) {
	    RoleMenu model = roleMenuDAO.load(id);
	    return toDTO(model);
    }

	public void updateAllFields(RoleMenuDTO dto) {
		RoleMenu model = roleMenuDAO.load(dto.getId());
		toModel(model, dto);
		roleMenuDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				//throw new BizException("未实现删除逻辑，请确认是物理删除还是逻辑删除");
				 roleMenuDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public RoleMenuDTO findOne(RoleMenuDTO query) {
		RoleMenu model = roleMenuDAO.findOne(query);
		return toDTO(model);
	}
	
	private List<RoleMenuDTO> toDTOs(List<RoleMenu> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<RoleMenuDTO>(0);
		}
		List<RoleMenuDTO> dtos = new ArrayList<RoleMenuDTO>(models.size());
		for (RoleMenu model : models) {
	        RoleMenuDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
    
    private RoleMenuDTO toDTO(RoleMenu model) {
		if (model == null) {
			return null;
		}
		RoleMenuDTO dto = BeanMapper.map(model, RoleMenuDTO.class);
		
		return dto;
	}
	
	private void toModel(RoleMenu model, RoleMenuDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<RoleMenu> toModels(List<RoleMenuDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<RoleMenu>(0);
		}
		List<RoleMenu> models = new ArrayList<RoleMenu>(dtos.size());
		for (RoleMenuDTO dto : dtos) {
	        RoleMenu model = new RoleMenu();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}

	public List<RoleMenuDTO> findByRole(Long roleId) {
		RoleMenuDTO query = new RoleMenuDTO();
		query.setRole(new Role());
		query.getRole().setId(roleId);
		return toDTOs(roleMenuDAO.find(query, null));
	}

	@Transactional
	public void update(Long roleId, Long[] menuIds) {
    	roleMenuDAO.deleteByRole(roleId);
    	for (Long menuId : menuIds) {
    		Menu m = menuDAO.load(menuId);
    		for (Menu subMenu : m.getChildren()) {
				RoleMenu rm = new RoleMenu();
				rm.setMenu(new Menu());
				rm.getMenu().setId(subMenu.getId());
				rm.setRole(new Role());
				rm.getRole().setId(roleId);
				roleMenuDAO.create(rm);
    		}
    		RoleMenu rm = new RoleMenu();
			rm.setMenu(new Menu());
			rm.getMenu().setId(menuId);
			rm.setRole(new Role());
			rm.getRole().setId(roleId);
			roleMenuDAO.create(rm);
		}
	}
}
