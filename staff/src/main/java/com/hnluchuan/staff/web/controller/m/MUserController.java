package com.hnluchuan.staff.web.controller.m;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnluchuan.staff.web.controller.BaseController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"用户操作"})
@Controller
@RequestMapping(value = "/m/user", produces={"application/json;charset=UTF-8", "text/html;charset=UTF-8"})
public class MUserController extends BaseController {

	@ResponseBody
	@RequestMapping("login")
	@ApiOperation(notes = "这是一个详细的描述，描述这个api的生平之类的，话说盘古开天辟地的时候，有一座山，山上有一座庙，......", httpMethod = "GET", value = "登陆")
	public String login() throws Exception {
		return ok();
	}
}
