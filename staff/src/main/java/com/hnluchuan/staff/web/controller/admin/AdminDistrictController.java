package com.hnluchuan.staff.web.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnluchuan.core.support.Page;
import com.hnluchuan.staff.dto.DistrictDTO;
import com.hnluchuan.staff.service.DistrictService;
import com.hnluchuan.staff.web.controller.BaseController;

@Controller
@RequestMapping(value = "/admin/district", produces="text/html;charset=UTF-8")
public class AdminDistrictController extends BaseController {

	@Autowired
	private DistrictService districtService;

	@RequestMapping(value = "/detail")
	public String detail(Map<String, Object> map, Long id) {
		if (id != null) {
			DistrictDTO dto = districtService.load(id);
			map.put("n", dto);
		}
		return "admin/district/district_detail";
	}
	
	@ResponseBody
	@RequestMapping(value = "/save")
	
	public String save(DistrictDTO dto) throws Exception {
		if (dto.getId() == null) {
			districtService.create(dto);
		} else {
			districtService.updateAllFields(dto);
		}
		return ok();
	}

    
	@ResponseBody
	@RequestMapping(value = "/delete") 
	public String delete(Long[] ids) throws IOException {
		districtService.deleteByIds(ids);
		return ok();
	}
	
	@RequestMapping(value = "/list")
	
	public String list(DistrictDTO dto, Map<String, Object> map, Page page) {
		List<DistrictDTO> dtos = districtService.find(dto, page);
		map.put("list", dtos);
		map.put("query", dto);
		return "admin/district/district_list";
	}
	
}
