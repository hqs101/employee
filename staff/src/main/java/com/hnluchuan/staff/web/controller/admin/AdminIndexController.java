package com.hnluchuan.staff.web.controller.admin;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnluchuan.staff.web.controller.CoreBaseController;

@Controller
@RequestMapping(value = "/admin/index", produces = "text/html;charset=UTF-8")
public class AdminIndexController extends CoreBaseController {
	
    @RequestMapping("index_v3")
    public String index_v3(Map<String, Object> map) throws Exception {
        return "admin/index_v3";
    }
    
}	
