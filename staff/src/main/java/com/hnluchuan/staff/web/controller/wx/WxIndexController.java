package com.hnluchuan.staff.web.controller.wx;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/wx/index", produces = "text/html;charset=UTF-8")
public class WxIndexController extends WxBaseController {
	
	@RequestMapping("")
	public String index(Map<String, Object> map) throws Exception {
		return "wx/wx_index";
	}
}
