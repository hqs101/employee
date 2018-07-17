package com.hnluchuan.staff.web.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnluchuan.staff.dto.MenuDTO;
import com.hnluchuan.staff.dto.RoleMenuDTO;
import com.hnluchuan.staff.model.Menu;
import com.hnluchuan.staff.model.Role;
import com.hnluchuan.staff.service.MenuService;

@Controller
public class IndexController extends BaseController {

	@Autowired
	private MenuService menuService;
	
    @RequestMapping(value = "/index")
    public String index(Map<String, Object> map) {    
    	return "redirect:/admin/index";
    }
    
	@RequestMapping(value = "/admin/index")
	public String adminIndex(Map<String, Object> map) {

		MenuDTO rootMenu = menuService.listForAdminPage();
		List<Role> roles = userRoleService.findRolesByUserId(getCurrentUserDTO().getId());
		List<RoleMenuDTO> roleMenuDTOs = new ArrayList<>();
		for (Role role : roles) {
			roleMenuDTOs.addAll(roleMenuService.findByRole(role.getId()));
		}
		for (Menu l1 : rootMenu.getChildren()) { // l1是一级菜单
			boolean none = true;
			for (Menu l2 : l1.getChildren()) { // l2是二级菜单
				if (!l2.getShow()) { // 不显示
					continue;
				}
				boolean show = ifContains(roleMenuDTOs, l2.getId());
				l2.setShow(show);
				if (show) {
					none = false;
				}
			}
			if (none) {
				l1.setShow(false);
			} else {
				l1.setShow(true);
			}
		}
		map.put("rootMenu", rootMenu);
		map.put("user", getCurrentUserDTO());
		
		return "admin/index";
	}
	
	private boolean ifContains(List<RoleMenuDTO> roleMenuDTOs, Long id) {
		for (RoleMenuDTO rm : roleMenuDTOs) {
			if (rm.getMenu().getId().longValue() == id) {
				return true;
			}
		}
		return false;
	}
	
	@RequestMapping(value = "/admin/login")
	public String adminLogin(Map<String, Object> map) {
	    if (new File("/Users/kevin/code").exists()) {
	        map.put("self", true);
	    }
		return "admin/login";
	}
	
}	
