package com.hnluchuan.staff.web.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnluchuan.core.support.Page;
import com.hnluchuan.core.support.Token;
import com.hnluchuan.staff.dto.ExampleDTO;
import com.hnluchuan.staff.service.ExampleService;
import com.hnluchuan.staff.web.controller.BaseController;

@Controller
@RequestMapping(value = "/admin/example", produces="text/html;charset=UTF-8")
public class AdminExampleController extends BaseController {

	@Autowired
	private ExampleService exampleService;

	@Token(save = true)
	@RequestMapping(value = "/detail")
	public String detail(Map<String, Object> map, Long id) {
		if (id != null) {
			ExampleDTO dto = exampleService.load(id);
			map.put("n", dto);
		}
		return "admin/example/example_detail";
	}

	@Token(remove = true)
	@ResponseBody
	@RequestMapping(value = "/save")
	public String save(ExampleDTO dto) throws Exception {
		assertTrue(StringUtils.isNotBlank(dto.getUsername()), "用户名不能为空");
		if (dto.getId() == null) {
			exampleService.create(dto);
		} else {
			exampleService.updateAllFields(dto);
		}
		return ok();
	}

    
	@ResponseBody
	@RequestMapping(value = "/delete") 
	public String delete(Long[] ids) throws IOException {
		exampleService.deleteByIds(ids);
		return ok();
	}
	
	@RequestMapping(value = "/list")
	//@PreAuthorize(value = "hasRole('merchant')")
	public String list(ExampleDTO dto, Map<String, Object> map, Page page) {
		List<ExampleDTO> dtos = exampleService.find(dto, page);
		map.put("list", dtos);
		map.put("query", dto);
		return "admin/example/example_list";
	}
	
}
