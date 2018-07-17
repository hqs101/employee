package com.hnluchuan.staff.web.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnluchuan.staff.dto.EducationDTO;
import com.hnluchuan.staff.service.EducationService;

import com.hnluchuan.core.support.Page;
import com.hnluchuan.staff.web.controller.BaseController;

@Controller
@RequestMapping(value = "/admin/education", produces="text/html;charset=UTF-8")
public class AdminEducationController extends BaseController {

	@Autowired
	private EducationService educationService;

	@RequestMapping(value = "/detail")
	public String detail(Map<String, Object> map, Long id) {
		if (id != null) {
			EducationDTO dto = educationService.load(id);
			map.put("n", dto);
		}
		return "admin/education/education_detail";
	}

	@ResponseBody
	@RequestMapping(value = "/save")
	public String save(EducationDTO dto) throws Exception {
		if (dto.getId() == null) {
			educationService.create(dto);
		} else {
			//throw new RuntimeException("请实现更新逻辑，并删除该行");
				EducationDTO old = educationService.load(dto.getId());
				// 确定哪些字段是在详情页面需要修改的，不在详情页修改的，就不需要在这里set值。updated和updatedBy不需要在这里处理，框架会统一处理
				old.setId(dto.getId());
				old.setEmployee(dto.getEmployee());
				old.setStart(dto.getStart());
				old.setEnd(dto.getEnd());
				old.setMajor(dto.getMajor());
				old.setRecord(dto.getRecord());
				old.setCertificate(dto.getCertificate());
				educationService.updateAllFields(old);
			}
		return ok();
	}

	@ResponseBody
	@RequestMapping(value = "/delete") 
	public String delete(Long[] ids) throws IOException {
		educationService.deleteByIds(ids);
		return ok();
	}
	
	@RequestMapping(value = "/list")
	public String list(EducationDTO dto, Boolean search, Map<String, Object> map, Page page) {
		List<EducationDTO> dtos = educationService.find(dto, page);
		map.put("list", dtos);
		map.put("query", dto);
		map.put("search", search);
		return "admin/education/education_list";
	}

	@RequestMapping(value = "/search")
	public String search(EducationDTO dto, Map<String, Object> map, Page page) {
		List<EducationDTO> dtos = educationService.find(dto, page);
		map.put("list", dtos);
		map.put("query", dto);
		return "admin/education/education_search";
	}
	
}
