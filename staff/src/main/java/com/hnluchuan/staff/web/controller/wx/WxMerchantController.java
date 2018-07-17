package com.hnluchuan.staff.web.controller.wx;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/wx/merchant", produces = "text/html;charset=UTF-8")
public class WxMerchantController extends WxBaseController {
	
	@RequestMapping("list")
	public String list(Map<String, Object> map) throws Exception {
		return "wx/wx_merchant_list";
	}
	
	@RequestMapping("detail")
	public String detail(Map<String, Object> map) throws Exception {
		return "wx/wx_merchant_detail";
	}
	
	@RequestMapping("buy")
	public String buy(Map<String, Object> map) throws Exception {
		return "wx/wx_merchant_buy";
	}
}
