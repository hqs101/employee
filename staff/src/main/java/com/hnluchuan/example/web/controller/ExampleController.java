package com.hnluchuan.example.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnluchuan.example.service.ExampleTransactionService;
import com.hnluchuan.staff.web.controller.BaseController;

@Controller
@RequestMapping(value = "/example")
public class ExampleController extends BaseController {

	@Autowired
	private ExampleTransactionService transactionExampleService;
	
	@ResponseBody
	@RequestMapping("testNested")
	public String testNested() throws Exception {
		transactionExampleService.a_new();
		return "ok";
	}
}
