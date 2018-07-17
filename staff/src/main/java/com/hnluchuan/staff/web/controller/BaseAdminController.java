package com.hnluchuan.staff.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alibaba.fastjson.JSONObject;

public class BaseAdminController extends BaseController {
	
    @ExceptionHandler
    public String onException(Exception e, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	logger.error(e.getMessage(), e);
    	if (isAjaxRequest()) {
    		JSONObject json = new JSONObject();
        	json.put("success", false);
        	json.put("msg", e.getMessage());
        	json.put("statusCode", 300);
    		json.put("message", e.getMessage());
    		response.setContentType("application/json; charset=UTF-8");
    		response.getWriter().print(json.toJSONString());
    		return null;
    	} else {
    		return failPage(e);
    	}
    	
    }
}
