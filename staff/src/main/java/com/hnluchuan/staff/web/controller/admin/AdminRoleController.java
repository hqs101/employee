package com.hnluchuan.staff.web.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hnluchuan.core.support.Page;
import com.hnluchuan.core.support.Token;
import com.hnluchuan.utils.common.JsonArrayUtils;
import com.hnluchuan.staff.common.Log;
import com.hnluchuan.staff.common.LogModule;
import com.hnluchuan.staff.common.MenuLevels;
import com.hnluchuan.staff.common.Module;
import com.hnluchuan.staff.dto.AuthorityDTO;
import com.hnluchuan.staff.dto.MenuDTO;
import com.hnluchuan.staff.dto.ModuleAuthorityDTO;
import com.hnluchuan.staff.dto.RoleDTO;
import com.hnluchuan.staff.dto.RoleMenuDTO;
import com.hnluchuan.staff.model.Authority;
import com.hnluchuan.staff.model.Menu;
import com.hnluchuan.staff.model.User;
import com.hnluchuan.staff.service.RoleAuthorityService;
import com.hnluchuan.staff.service.RoleService;
import com.hnluchuan.staff.util.SpringSecurityUtils;
import com.hnluchuan.staff.web.controller.BaseController;

@Module(name = LogModule.Role)
@Controller
@RequestMapping(value = "/admin/role", produces="text/html;charset=UTF-8")
public class AdminRoleController extends BaseController {

	/**
	 * @Autowired 表示自动注入spring管理的bean，不需要写getter和setter就能注入
	 */
	@Autowired
	private RoleService roleService;
	@Autowired
	private RoleAuthorityService roleAuthorityService;
	
	/**
	 * map是用来传值 ，在页面通过${key}的方式就能得到值 。比如map里put("role", dto)，在页面取就是${role.id}这种。
	 * @param map
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/detail")
	@Token(save = true)
	public String detail(Map<String, Object> map, Long id) {
		/*
		List<AuthorityDTO> authorities = authorityService.listTops();
		map.put("authorities", authorities);
		*/
		/*
		if (id != null) {
			RoleDTO dto = roleService.load(id);
			if (dto.getAuthorities() != null) {
				List<String> list = new ArrayList<String>();
				for (Authority a : dto.getAuthorities()) {
	                list.add(a.getName());
	                for (AuthorityDTO aDTO : authorities) {
	                    if (aDTO.getName().equals(a.getName())) {
	                    	aDTO.setContains(true);
	                    	continue;
	                    } else {
	                    	processChildren(aDTO.getChildren(), a.getName());
	                    }
                    }
                }
				dto.setAuthorityNames(list);
			}
			map.put("role", dto);
		} */
		if (id != null) {
			RoleDTO n = roleService.load(id);
			map.put("n", n);
		}
		return "admin/role/role_detail";
	}
	
	/**
	 * 如果dto.getId不为空，则为更新，如果为空，则是保存.
	 * @ResponseBody 表示这是一个接收json请求的方式，返回值 不会跳转到哪个页面，返回值为void。
	 * 要传给页面的json值，请调用父类BaseController提供的三个方法。
	 * @param dto
	 */
	@ResponseBody
	@RequestMapping(value = "/save")
	@Log(operation = "保存角色")
	@Token(remove = true)
	public String save(HttpServletResponse response, RoleDTO dto) throws IOException {
		if (dto.getId() == null) {
			dto.setEditable(true);
			roleService.save(dto);
		} else {
			roleService.update(dto);
		}
		return ok();
	}
	
	@ResponseBody
	
	@RequestMapping(value = "/delete") 
	public String delete(HttpServletResponse response, Long[] ids) throws IOException {
		roleService.deleteByIds(ids);
		
		// 如果是删除，不需要关闭当前tab
		// resultDTO.setCallbackType(CallbackType.closeCurrent);
		return ok();
	}
	
	@RequestMapping(value = "/list")
	public String list(RoleDTO dto, Map<String, Object> map, Page page) {
		map.put("list", roleService.list(dto, page));
		return "admin/role/role_list";
	}
	
	@ResponseBody
	@RequestMapping(value = "/find")
	public void find() {
		
	}
	
	@Log(operation = "test")
    @RequestMapping("test")
    public String test() throws Exception {
        return null;
    }
	
    @RequestMapping("authorityDetail")
    public String authorityDetail(Long roleId, Map<String, Object> map) throws Exception {

        /*List<AuthorityDTO> authorities = authorityService.listTops();
        List<Authority> ownedAuthorities = roleAuthorityService.findByAuthoritiesByRoleId(roleId);
        for (AuthorityDTO a : authorities) {
            for (Authority b : ownedAuthorities) {
                if (b.getId().equals(a.getId())) {
                    a.setChecked(true);
                    break;
                }
             }
        }
        map.put("authorities", authorities);*/
    	List<ModuleAuthorityDTO> moduleAuthorityDTOs = authorityService.findModuleAuthories();
    	User user = SpringSecurityUtils.getCurrentUser();
    	List<Authority> authoritiesDb = roleAuthorityService.findByAuthoritiesByRoleId(roleId);
    	for (ModuleAuthorityDTO moduleAuthorityDTO : moduleAuthorityDTOs) {
			for (Authority a : moduleAuthorityDTO.getAuthorities()) {
				for (Authority dbAuthority : authoritiesDb) {
					if (StringUtils.isNotBlank(dbAuthority.getCode()) && dbAuthority.getCode().equals(a.getCode())) {
						a.setContains(true);
					}
				}
			}
		}
    	map.put("moduleAuthorities", moduleAuthorityDTOs);
    	map.put("roleId", roleId);
        return "admin/role/role_authorityDetail";
    
    }
    
    @ResponseBody
    @RequestMapping("getAuthorities")
    public String getAuthorities(Long roleId) throws Exception {
    	List<AuthorityDTO> authorities = authorityService.listTops();
        List<Authority> ownedAuthorities = roleAuthorityService.findByAuthoritiesByRoleId(roleId);
        List<Long> existIds = new ArrayList<Long>();
        for (Authority b : ownedAuthorities) {
            existIds.add(b.getId());
        }
        for (AuthorityDTO a : authorities) {
        	a.setText(a.getName());
        	if (null == a.getState()) {
    			a.setState(new AuthorityDTO().getState());
    		}
        	a.getState().setOpened(false);
        	List<AuthorityDTO> childrens = new ArrayList<AuthorityDTO>();
        	for (Authority auth : a.getChildrens()) {
				AuthorityDTO dto = new AuthorityDTO(auth.getId(), auth.getCode(), auth.getName());
				dto.setId(auth.getId());
				dto.setText(auth.getName());
				if (existIds.contains(auth.getId())) {
					dto.getState().setSelected(true);
				}
				childrens.add(dto);
			}
        	a.setChildren(childrens);
        	if (existIds.contains(a.getId())) {
        		a.getState().setSelected(true);
			}
        }
        return JsonArrayUtils.toJsonArray(authorities, null);
    }
    
    @ResponseBody
    @RequestMapping("updateAuthority")
    public String updateAuthority(Long roleId, Long[] authorityIds) throws Exception {
        roleAuthorityService.update(roleId, authorityIds);
        return ok();
    }
	
	private void processChildren(List<Authority> children, String name) {
		if (CollectionUtils.isEmpty(children)) {
			return;
		}
		for (Authority child : children) {
			if (child.getName().equals(name)) {
				child.setContains(true);
			} else {
				processChildren(child.getChildrens(), name);
			}
		}
    }
	
	@RequestMapping("roleMenu")
	public String roleMenu(Long roleId) throws Exception {
		return "admin/role/role_roleMenu";
	}
	
	@ResponseBody
	@RequestMapping("getMenus")
	public String getMenus(Long roleId) throws Exception {
		List<RoleMenuDTO> roleMenuDTOs = roleMenuService.findByRole(roleId);
		MenuDTO query = new MenuDTO();
		query.setLevel(MenuLevels.Level0.level());
		MenuDTO rootMneu = menuService.findOne(query);

		JSONArray result = new JSONArray();
		for (Menu l1 : rootMneu.getChildren()) {
			JSONObject j1 = new JSONObject();
			j1.put("text", l1.getName());
			j1.put("id", l1.getId());
			JSONArray array = new JSONArray();
			for (Menu l2 : l1.getChildren()) {
				JSONObject j2 = new JSONObject();
				j2.put("text", l2.getName());
				j2.put("id", l2.getId());
				JSONObject state = new JSONObject();
				state.put("disabled", false);
				state.put("opened", false);
				boolean contains = ifContains(roleMenuDTOs, l2.getId());
				state.put("selected", contains);
				j2.put("state", state);
				array.add(j2);
			}
			j1.put("children", array);
			result.add(j1);
		}
		
    	return result.toJSONString();
    
	}

	private boolean ifContains(List<RoleMenuDTO> roleMenuDTOs, Long id) {
		for (RoleMenuDTO rm : roleMenuDTOs) {
			if (rm.getMenu().getId().longValue() == id) {
				return true;
			}
			for (Menu m : rm.getMenu().getChildren()) {
				if (m.getId().longValue() == id) {
					return true;
				}
			}
		}
		return false;
	}
	
	@ResponseBody
    @RequestMapping("updateRoleMenu")
	public String updateRoleMenu(Long roleId, Long[] menuIds) throws Exception {
        roleMenuService.update(roleId, menuIds);
        return ok();
    }
}
