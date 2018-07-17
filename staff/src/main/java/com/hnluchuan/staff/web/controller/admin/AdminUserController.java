package com.hnluchuan.staff.web.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnluchuan.core.support.Page;
import com.hnluchuan.staff.common.UserType;
import com.hnluchuan.staff.dto.RoleDTO;
import com.hnluchuan.staff.dto.UserDTO;
import com.hnluchuan.staff.model.Role;
import com.hnluchuan.staff.service.RoleService;
import com.hnluchuan.staff.service.UserRoleService;
import com.hnluchuan.staff.service.UserService;
import com.hnluchuan.staff.web.controller.BaseAdminController;

@Controller
@RequestMapping(value = "/admin/user", produces="text/html;charset=UTF-8")
public class AdminUserController extends BaseAdminController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value = "/detail")
	public String detail(Integer type, Long parentId, Map<String, Object> map, Long id, boolean seedUser) {
		if (id != null) {
			UserDTO dto = userService.load(id);
			map.put("n", dto);
		}
		map.put("seedUser", seedUser);
		map.put("provinces", provinceService.findAll());
		map.put("type", type);
		map.put("parentId", parentId);
		return "admin/user/user_detail";
	}
	
	@PreAuthorize("hasRole('user.add')")
	@ResponseBody
	@RequestMapping(value = "/save")
	public String save(UserDTO dto) throws Exception {
		if (dto.getId() == null) {
			dto.setType(UserType.Normal.getValue());
			Long id = userService.create(dto);
		} else {
			UserDTO old = userService.load(dto.getId());
			userService.updateAllFields(old);
		}
		return ok();
	}

    
	@ResponseBody
	@RequestMapping(value = "/delete") 
	public String delete(Long[] ids) throws IOException {
		userService.deleteByIds(ids);
		return ok();
	}
	
	@RequestMapping(value = "/list")
	public String list(Integer fromType, Integer type, UserDTO dto, Map<String, Object> map, Page page) {
		List<UserDTO> dtos = userService.find(dto, page);
		map.put("list", dtos);
		map.put("query", dto);
		map.put("type", type);
		map.put("fromType", fromType);
		return "admin/user/user_list";
	}
	
	@RequestMapping(value = "/auth")
	public String auth(Map<String, Object> map, Long userId, RoleDTO dto, Page page) {
		if (userId != null) {
			List<Role> roles = userRoleService.findRolesByUserId(userId);
			List<RoleDTO> list = roleService.list(dto, page);
			for (RoleDTO model : list) {
				for (Role role : roles) {
					if (role.getId().longValue() == model.getId()) {
						model.setChecked(true);
					}
				}
			}
			map.put("userId", userId);
			map.put("list", list);
			map.put("query", dto);
		}
		return "admin/user/user_auth";
	}
	
	@RequestMapping(value = "/authSave")
	@ResponseBody
	public String authSave(Long[] ids, Long userId) {
		userRoleService.save(ids, userId);
		return ok();
	}
	
	@RequestMapping(value = "/reset")
	public String reset(Map<String, Object> map) {
		UserDTO dto = getCurrentUserDTO();
		map.put("n", dto);
		return "admin/user/user_reset";
	}
	
	@RequestMapping(value = "/editHeadImage")
	public String editHeadImage(Map<String, Object> map) {
		map.put("user", getCurrentUserDTO());
		return "admin/user/user_editHeadImage";
	}
	
	@RequestMapping("saveHeadImage")
	public String saveHeadImage(HttpServletRequest request) throws Exception {
		String file = saveFileAndReturnFileName(request, "file");
		UserDTO user = userService.load(getCurrentUserDTO().getId());
		if (StringUtils.isNotBlank(file)) {
			user.setHeadImage(file);
			userService.updateAllFields(user);
		}
		return successPage(null);
	}
	
	@ResponseBody
	@RequestMapping(value = "/resetPassword") 
	public String resetPassword(String newPassword, Long userId) {
			userService.resetPassword(newPassword, userId);
		return ok();
	}

	@RequestMapping(value = "changePassword")
	public String changePassword(Map<String, Object> map) {
		UserDTO dto = getCurrentUserDTO();
		map.put("n", dto);
		return "admin/user/user_changePassword";
	}
	
	@RequestMapping(value = "saveChangePassword")
	public String saveChangePassword(String oldPassword, String newPassword, String confirmPassword, Long userId, Map<String, Object> map) {
		userService.changePassword(userId, oldPassword, newPassword, confirmPassword);
		return successPage(null);
	}
	
}
