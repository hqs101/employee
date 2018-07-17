package com.hnluchuan.staff.web.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnluchuan.core.support.Page;
import com.hnluchuan.staff.dto.CityDTO;
import com.hnluchuan.staff.service.CityService;
import com.hnluchuan.staff.web.controller.BaseController;

@Controller
@RequestMapping(value = "/admin/city", produces="text/html;charset=UTF-8")
public class AdminCityController extends BaseController {

	@Autowired
	private CityService cityService;

	@RequestMapping(value = "/detail")
	public String detail(Map<String, Object> map, Long id) {
		if (id != null) {
			CityDTO dto = cityService.load(id);
			map.put("n", dto);
		}
		return "admin/city/city_detail";
	}
	
	@ResponseBody
	@RequestMapping(value = "/save")
	
	public String save(CityDTO dto) throws Exception {
		if (dto.getId() == null) {
			cityService.create(dto);
		} else {
			cityService.updateAllFields(dto);
		}
		return ok();
	}

    
	@ResponseBody
	@RequestMapping(value = "/delete") 
	public String delete(Long[] ids) throws IOException {
		cityService.deleteByIds(ids);
		return ok();
	}
	
	@RequestMapping(value = "/list")
	
	public String list(CityDTO dto, Map<String, Object> map, Page page) {
		List<CityDTO> dtos = cityService.find(dto, page);
		map.put("list", dtos);
		map.put("query", dto);
		return "admin/city/city_list";
	}
	
}
