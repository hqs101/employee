package com.hnluchuan.staff.web.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnluchuan.staff.web.controller.BaseController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = {"示例模块", "这是一个比较长的描述"})
@Controller
@RequestMapping(value = "/api/example", produces = "application/json; charset=UTF-8")
public class ApiExampleController extends BaseController {

	@ApiOperation(value = "示例列表")
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(@ApiParam(value = "姓名", required = false) String name) throws Exception {
		System.out.println("list...");
		return ok();
	}
}
