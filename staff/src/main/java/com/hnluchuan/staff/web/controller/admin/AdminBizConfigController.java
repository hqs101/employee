package com.hnluchuan.staff.web.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnluchuan.core.support.Page;
import com.hnluchuan.staff.dto.BizConfigDTO;
import com.hnluchuan.staff.service.BizConfigService;
import com.hnluchuan.staff.web.controller.BaseController;

@Controller
@RequestMapping(value = "/admin/bizConfig", produces="text/html;charset=UTF-8")
public class AdminBizConfigController extends BaseController {

	@Autowired
	private BizConfigService bizConfigService;

	@RequestMapping(value = "/detail")
	public String detail(Map<String, Object> map, Long id) {
		if (id != null) {
			BizConfigDTO dto = bizConfigService.load(id);
			map.put("n", dto);
		}
		return "admin/bizConfig/bizConfig_detail";
	}
	
	@ResponseBody
	@RequestMapping(value = "/save")
	public String save(BizConfigDTO dto) throws Exception {
		if (dto.getId() == null) {
			bizConfigService.create(dto);
		} else {
			BizConfigDTO old = bizConfigService.load(dto.getId());
			old.setValue(dto.getValue());
			bizConfigService.updateAllFields(old);
		}
		return ok();
	}

    
	@ResponseBody
	@RequestMapping(value = "/delete") 
	public String delete(Long[] ids) throws IOException {
		bizConfigService.deleteByIds(ids);
		return ok();
	}
	
	@RequestMapping(value = "/list")
	public String list(BizConfigDTO dto, Map<String, Object> map, Page page) {
		List<BizConfigDTO> dtos = bizConfigService.find(dto, page);
		map.put("list", dtos);
		map.put("query", dto);
		return "admin/bizConfig/bizConfig_list";
	}
	
}
