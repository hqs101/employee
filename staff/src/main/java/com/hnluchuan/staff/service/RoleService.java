package com.hnluchuan.staff.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnluchuan.core.support.Page;
import com.hnluchuan.core.utils.ValidationUtils;
import com.hnluchuan.utils.common.BeanMapper;
import com.hnluchuan.utils.exception.BizException;
import com.hnluchuan.staff.dao.AuthorityDAO;
import com.hnluchuan.staff.dao.RoleDAO;
import com.hnluchuan.staff.dto.RoleDTO;
import com.hnluchuan.staff.model.Role;

/**
 * Service层，类要加@Service标识
 * 一般都是由service层操作数据库，
 * 并且，只有save, update, delete等开头的方法，才有新增修改删除数据库表的权限，
 * 其它的方法名只有查询的权限，这是事务管理，具体哪些方法名可以指定这种权限，是在applicationContext.xml文件的txAdvice定义处配置。
 * @author Kewen.Zhu
 *
 */
@Service
public class RoleService {
	
	@Autowired
	private RoleDAO roleDAO;
	
	public List<RoleDTO> list(RoleDTO dto, Page page) {
		List<Role> models = roleDAO.find(dto, page);
		List<RoleDTO> dtos = toDTOs(models);
		return dtos;
	}
	
	public RoleDTO findOne(RoleDTO query) {
		Role model = roleDAO.uniqueFind(query);
		return toDTO(model);
	}

	/**
	 * 将model集合转为dto集合
	 * @param models
	 * @return
	 */
	private List<RoleDTO> toDTOs(List<Role> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<RoleDTO>(0);
		}
		List<RoleDTO> dtos = new ArrayList<RoleDTO>(models.size());
		for (Role model : models) {
	        RoleDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
	
	/**
	 * 将model转为dto。属性名和类型相同的部分调用BeanMapper.map()方法，不同的单独转换。
	 * @param model
	 * @return
	 */
	private RoleDTO toDTO(Role model) {
		if (model == null) {
			return null;
		}
		RoleDTO dto = BeanMapper.map(model, RoleDTO.class);
		
		return dto;
	}
	
	public void save(RoleDTO dto) {
		Role model = new Role();
		toModel(model, dto);
		processAuthority(model, dto);
	    ValidationUtils.assertTrue(StringUtils.isNotBlank(model.getName()), "角色名不能为空");
	    
		roleDAO.create(model);
	}

	private void toModel(Role model, RoleDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<Role> toModels(List<RoleDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<Role>(0);
		}
		List<Role> models = new ArrayList<Role>(dtos.size());
		for (RoleDTO dto : dtos) {
	        Role model = new Role();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}

	public RoleDTO load(Long id) {
	    Role model = roleDAO.load(id);
	    return toDTO(model);
    }

	public void update(RoleDTO dto) {
		Role model = roleDAO.load(dto.getId());
		if (model.getEditable() != null && !model.getEditable()) {
			throw new BizException("当前角色不可修改");
		}
		// set values
		
		//processAuthority(model, dto);
	    toModel(model, dto);
		System.out.println("正确值为："+model.getName());
		roleDAO.update(model);
    }
	
	public void updateAllField(RoleDTO dto) {
		Role model = roleDAO.load(dto.getId());
		toModel(model, dto);
		roleDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				if (id == 1) {
					throw new BizException("禁止删除管理员角色");
				}
				roleDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		roleDAO.deleteById(id);
	}

	public RoleDTO uniqueFind(RoleDTO dto) {
		Role model = roleDAO.uniqueFind(dto);
		return toDTO(model);
	}
	
	private void processAuthority(Role model, RoleDTO dto) { /*
	    ValidationUtils.assertTrue(StringUtils.isNotBlank(dto.getName()), "角色名不能为空");
	    model.setName(dto.getName());
		if (CollectionUtils.isEmpty(dto.getAuthorityIds())) {
			model.setAuthorities(null);
			return;
		}
		Set<Authority> set = new HashSet<Authority>();
		List<Long> authorityIds = dto.getAuthorityIds();
		if (CollectionUtils.isNotEmpty(authorityIds)) {
			for (Long id : authorityIds) {
	            Authority a = authorityDAO.load(id);
	            set.add(a);
	            if (a.getParentAuthority() != null) {
	                set.add(a.getParentAuthority());
	            }
	            // set.addAll(processAuthorityChildren(a));
            }
		}
		
		model.setAuthorities(set); */
    }
	
	/*private Collection<? extends Authority> processAuthorityChildren(Authority a) {
		if (CollectionUtils.isEmpty(a.getChildren())) {
			return new HashSet<Authority>(0);
		}
		Set<Authority> set = new HashSet<Authority>();
		for (Authority child : a.getChildren()) {
	        set.add(child);
	        set.addAll(processAuthorityChildren(child));
        }
	    return set;
    }*/
	
	@Autowired
	private AuthorityDAO authorityDAO;
}
