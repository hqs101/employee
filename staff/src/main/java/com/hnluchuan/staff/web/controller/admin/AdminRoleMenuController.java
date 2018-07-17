package com.hnluchuan.staff.web.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnluchuan.core.support.Page;
import com.hnluchuan.utils.exception.BizException;
import com.hnluchuan.staff.dto.RoleMenuDTO;
import com.hnluchuan.staff.service.RoleMenuService;
import com.hnluchuan.staff.web.controller.BaseController;

@Controller
@RequestMapping(value = "/admin/roleMenu", produces="text/html;charset=UTF-8")
public class AdminRoleMenuController extends BaseController {

	@Autowired
	private RoleMenuService roleMenuService;

	@RequestMapping(value = "/detail")
	public String detail(Map<String, Object> map, Long id) {
		if (id != null) {
			RoleMenuDTO dto = roleMenuService.load(id);
			map.put("n", dto);
		}
		return "admin/roleMenu/roleMenu_detail";
	}
	
	@ResponseBody
	@RequestMapping(value = "/save")
	@PreAuthorize("hasRole('roleMenu')")
	public String save(RoleMenuDTO dto) throws Exception {
		if (dto.getId() == null) {
			roleMenuService.create(dto);
		} else {
			//RoleMenuDTO old = roleMenuService.load(dto.getId());
			// old.setXXX(dto.getXXX());
			//roleMenuService.updateAllFields(old);
			if (1 == 1) {
				throw new BizException("请实现更新逻辑");
			} 
		}
		return ok();
	}

    @PreAuthorize("hasRole('roleMenu')")
	@ResponseBody
	@RequestMapping(value = "/delete") 
	public String delete(Long[] ids) throws IOException {
		roleMenuService.deleteByIds(ids);
		return ok();
	}
	
	@RequestMapping(value = "/list")
	public String list(RoleMenuDTO dto, Map<String, Object> map, Page page) {
		List<RoleMenuDTO> dtos = roleMenuService.find(dto, page);
		map.put("list", dtos);
		map.put("query", dto);
		return "admin/roleMenu/roleMenu_list";
	}
	
}
