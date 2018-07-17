package com.hnluchuan.staff.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hnluchuan.core.support.Page;
import com.hnluchuan.core.utils.ValidationUtils;
import com.hnluchuan.utils.common.BeanMapper;
import com.hnluchuan.utils.exception.BizException;
import com.hnluchuan.staff.common.UserType;
import com.hnluchuan.staff.dao.UserDAO;
import com.hnluchuan.staff.dto.UserDTO;
import com.hnluchuan.staff.model.User;
import com.hnluchuan.staff.security.PasswordEncoder;

@Service
public class UserService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDAO userDAO;

	public List<UserDTO> find(UserDTO query, Page page) {
		List<User> models = userDAO.find(query, page);
		List<UserDTO> dtos = toDTOs(models);
		return dtos;
	}
	
	public List<UserDTO> findAll() {
		return this.find(new UserDTO(), null);
	}
	
	public List<UserDTO> find(int count) {
		Page page = new Page();
		page.setPage(1);
		page.setPageSize(count);
		return this.find(new UserDTO(), page);
	}
	
	public Long create(UserDTO dto) {
		User model = new User();
		toModel(model, dto);
		Long id = userDAO.create(model);
		return id;
	}
	
	public UserDTO load(Long id) {
	    User model = userDAO.load(id);
	    return toDTO(model);
    }

	public void updateAllFields(UserDTO dto) {
		User model = userDAO.load(dto.getId());
		toModel(model, dto);
		userDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				if (id == 1) {
					throw new BizException("禁止删除管理员");
				}
				userDAO.deleteById(id);
				/*
				User user = userDAO.load(id);
				user.setDeleted(true);
				userDAO.update(user);
				*/
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}
	
	public UserDTO findOne(UserDTO query) {
		User model = userDAO.findOne(query);
		return toDTO(model);
	}
	
	public List<UserDTO> toDTOs(List<User> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<UserDTO>(0);
		}
		List<UserDTO> dtos = new ArrayList<UserDTO>(models.size());
		for (User model : models) {
	        UserDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
    
    private UserDTO toDTO(User model) {
		if (model == null) {
			return null;
		}
		UserDTO dto = BeanMapper.map(model, UserDTO.class);
		
		return dto;
	}
	
	private void toModel(User model, UserDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<User> toModels(List<UserDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<User>(0);
		}
		List<User> models = new ArrayList<User>(dtos.size());
		for (UserDTO dto : dtos) {
	        User model = new User();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}
    
    public UserDTO login(String username, String password) {
		ValidationUtils.assertTrue(StringUtils.isNotBlank(username), "用户名不能为空");
		ValidationUtils.assertTrue(StringUtils.isNotBlank(password), "密码不能为空");
		
		UserDTO query = new UserDTO();
		query.setUsername(username);
		query.setPassword(passwordEncoder.encodePassword(password, username));
		
		User user = userDAO.findOne(query);
		if (user == null || !username.equalsIgnoreCase(user.getUsername())) {
			return null;
		} 
		return toDTO(user);
    }

    @Transactional
	public void resetPassword(String newPassword, Long userId) {
		User user = userDAO.load(userId);
		if ( user == null ){
			throw new BizException("该用户不存在");
		}
		String newPwd = passwordEncoder.encodePassword(newPassword, user.getUsername());
		user.setPassword(newPwd);
		userDAO.update(user);
	}

	public UserDTO findByOpenid(String openid) {
		User user = userDAO.loadUserByUsername(openid);
		return toDTO(user);
	}

	public UserDTO createFromWx(String openid, String nickName) {
		UserDTO dto = new UserDTO();
		dto.setUsername(openid);
		dto.setNickName(nickName);
		dto.setType(UserType.WX.getValue());
		Long id = create(dto);
		return load(id);
	}

	@Transactional
	public void changePassword(Long userId, String oldPassword, String newPassword, String confirmPassword) {
		User user = userDAO.load(userId);
		if (!passwordEncoder.encodePassword(oldPassword, user.getUsername()).equals(user.getPassword())) {
			throw new BizException("旧密码不正确");
		}
		if (StringUtils.isBlank(newPassword) || !newPassword.equals(confirmPassword)) {
			throw new BizException("两次新密码不匹配");
		}
		this.resetPassword(newPassword, userId);
	}
    
}
