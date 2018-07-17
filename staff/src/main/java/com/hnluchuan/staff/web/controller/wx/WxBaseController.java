package com.hnluchuan.staff.web.controller.wx;

import org.apache.commons.lang3.StringUtils;

import com.hnluchuan.utils.common.MyWebUtils;
import com.hnluchuan.staff.web.controller.BaseController;

public class WxBaseController extends BaseController {
	
	protected String getRequestUrlWithQueryString() {
        String url = MyWebUtils.getCurrentRequest().getRequestURL().toString();
        String q = MyWebUtils.getCurrentRequest().getQueryString();
        if (StringUtils.isNotBlank(q)) {
            url += "?" + q;
        }
        return url;
    }
	/*
	protected void setCustomerToSession(CustomerDTO customer) {
		MyWebUtils.getCurrentRequest().getSession().setAttribute("wxuser", customer);
	}
	
	protected CustomerDTO getCustomerFromSession() {
		return (CustomerDTO) MyWebUtils.getCurrentRequest().getSession().getAttribute("wxuser");
	} */
}
