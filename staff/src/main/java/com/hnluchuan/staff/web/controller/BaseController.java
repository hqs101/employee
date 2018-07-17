package com.hnluchuan.staff.web.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hnluchuan.core.support.TokenInterceptor;
import com.hnluchuan.utils.common.DevUtils;
import com.hnluchuan.utils.common.MyWebUtils;
import com.hnluchuan.utils.exception.KException;
import com.hnluchuan.staff.dto.UserDTO;
import com.hnluchuan.staff.service.FamilyService;
import com.hnluchuan.staff.service.ExperienceService;
import com.hnluchuan.staff.service.EmergencyContactService;
import com.hnluchuan.staff.service.EducationService;
import com.hnluchuan.staff.service.EmployeeService;
import com.hnluchuan.staff.service.AuthorityService;
import com.hnluchuan.staff.service.BizConfigService;
import com.hnluchuan.staff.service.CityService;
import com.hnluchuan.staff.service.DistrictService;
import com.hnluchuan.staff.service.MenuService;
import com.hnluchuan.staff.service.ProvinceService;
import com.hnluchuan.staff.service.RoleMenuService;
import com.hnluchuan.staff.service.StudentService;
import com.hnluchuan.staff.service.UserRoleService;
import com.hnluchuan.staff.service.UserService;
import com.hnluchuan.staff.util.SpringSecurityUtils;

public class BaseController extends CoreBaseController {
    @Autowired
    protected FamilyService familyService;

    @Autowired
    protected ExperienceService experienceService;

    @Autowired
    protected EmergencyContactService emergencyContactService;

    @Autowired
    protected EducationService educationService;

    @Autowired
    protected EmployeeService employeeService;

    @Autowired
    protected StudentService studentService;

	protected static Logger logger = Logger.getLogger(BaseController.class);
	
	@Autowired
	protected MenuService menuService;
	@Autowired
	protected AuthorityService authorityService;
	@Autowired
    protected UserRoleService userRoleService;
    @Autowired
    protected RoleMenuService roleMenuService;

    @Autowired
    protected BizConfigService bizConfigService;

    @Autowired
    protected CityService cityService;

    @Autowired
    protected ProvinceService provinceService;

    @Autowired
    protected DistrictService districtService;

   
	@Autowired
	protected UserService userService;
	
    protected UserDTO getUserFromSession() {
        if (DevUtils.isLocal()) {
            return userService.load(13L);
        }
        UserDTO user = null;
        Long userIdFromSession = (Long) MyWebUtils.getCurrentRequest().getSession().getAttribute("wx.userId");
        if (userIdFromSession != null) {
            user = userService.load(userIdFromSession);
        }
        return user;
    }

    protected void setUserToSession(UserDTO user) {
        if (user != null) {
            MyWebUtils.getCurrentRequest().getSession().setAttribute("wx.userId", user.getId());
        }
    }
    
    protected String getRequestUrlWithQueryString() {
        String url = MyWebUtils.getCurrentRequest().getRequestURL().toString();
        String q = MyWebUtils.getCurrentRequest().getQueryString();
        if (StringUtils.isNotBlank(q)) {
            url += "?" + q;
        }
        return url;
    }
    
    @ResponseBody
    public String okJson() {
    	JSONObject json = new JSONObject();
    	json.put("success", true);
    	return json.toJSONString();
    }
    
    protected UserDTO getCurrentUserDTO() {
    	if (SpringSecurityUtils.getCurrentUser() != null) {
    		return userService.load(SpringSecurityUtils.getCurrentUser().getId());
    	} else {
    		Long userId = (Long) MyWebUtils.getCurrentRequest().getAttribute("currentUserId");
    		if (userId != null) {
    			return userService.load(userId);
    		} else {
    			return null;
    		}
    	}
    }
    
    protected String successPage(String msg) {
    	if (StringUtils.isBlank(msg)) {
    		msg = "操作成功";
    	}
    	MyWebUtils.getCurrentRequest().setAttribute("msg", msg);
    	return "common/success";
    }
    
    protected String failPage(Exception e) {
    	String msg = e.getMessage();
    	if (StringUtils.isBlank(msg)) {
    		msg = "系统错误";
    	}
    	MyWebUtils.getCurrentRequest().setAttribute("msg", msg);
    	MyWebUtils.getCurrentRequest().setAttribute("stacks", e.getStackTrace());
    	return "common/fail";
    }
    
    protected String failPage(String msg) {
    	if (StringUtils.isBlank(msg)) {
    		msg = "操作成功";
    	}
    	MyWebUtils.getCurrentRequest().setAttribute("msg", msg);
    	return "common/fail";
    }
    protected boolean isAjaxRequest() {
    	String requestType = MyWebUtils.getCurrentRequest().getHeader("X-Requested-With");  
    	return StringUtils.isNotBlank(requestType);
    }
    
    @ResponseBody
    @ExceptionHandler
    public String onException(Exception e, HttpServletRequest request) {
    	// 把token还原回去
    	String token = request.getParameter(TokenInterceptor.FORM_NAME);
    	if (StringUtils.isNotBlank(token)) {
    		@SuppressWarnings("unchecked")
			Set<String> set = (Set<String>) request.getSession().getAttribute(TokenInterceptor.SET_KEY);
    		if (set != null) {
    			set.add(token);
    		}
    	}
    	String msg = e.getMessage();
    	if (e instanceof KException) {
    		msg = e.getMessage();
    	} else {
    		logger.error(e.getMessage(), e);
    	}
    	if (e instanceof HibernateOptimisticLockingFailureException) {
    	    msg = "请勿重复操作！";
        }
        JSONObject json = new JSONObject();
        json.put("success", false);
        json.put("msg", msg);
        return json.toJSONString();
    }
}
