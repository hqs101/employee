package com.hnluchuan.staff.web.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnluchuan.core.support.Page;
import com.hnluchuan.utils.exception.BizException;
import com.hnluchuan.staff.dto.StudentDTO;
import com.hnluchuan.staff.service.StudentService;
import com.hnluchuan.staff.web.controller.BaseController;

@Controller
@RequestMapping(value = "/admin/student", produces="text/html;charset=UTF-8")
public class AdminStudentController extends BaseController {

	@Autowired
	private StudentService studentService;

	@RequestMapping(value = "/detail")
	public String detail(Map<String, Object> map, Long id) {
		if (id != null) {
			StudentDTO dto = studentService.load(id);
			map.put("n", dto);
		}
		return "admin/student/student_detail";
	}
	
	@ResponseBody
	@RequestMapping(value = "/save")
	public String save(StudentDTO dto) throws Exception {
		if (dto.getId() == null) {
			studentService.create(dto);
		} else {
			throw new BizException("请实现更新逻辑，并删除该行");
			/*
			StudentDTO old = studentService.load(dto.getId());
			// 确定哪些字段是在详情页面需要修改的，不在详情页修改的，就不需要在这里set值。updated和updatedBy不需要在这里处理，框架会统一处理
			old.setId(dto.getId);
			old.setName(dto.getName);
			old.setAge(dto.getAge);
			studentService.updateAllFields(old);
			*/
		}
		return ok();
	}

	@ResponseBody
	@RequestMapping(value = "/delete") 
	public String delete(Long[] ids) throws IOException {
		studentService.deleteByIds(ids);
		return ok();
	}
	
	@RequestMapping(value = "/list")
	public String list(StudentDTO dto, Boolean search, Map<String, Object> map, Page page) {
		List<StudentDTO> dtos = studentService.find(dto, page);
		map.put("list", dtos);
		map.put("query", dto);
		map.put("search", search);
		return "admin/student/student_list";
	}
	
	@RequestMapping(value = "/search")
	public String search(StudentDTO dto, Map<String, Object> map, Page page) {
		List<StudentDTO> dtos = studentService.find(dto, page);
		map.put("list", dtos);
		map.put("query", dto);
		return "admin/student/student_search";
	}
	
}
