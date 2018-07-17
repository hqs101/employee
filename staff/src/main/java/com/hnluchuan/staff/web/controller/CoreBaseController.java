package com.hnluchuan.staff.web.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.hnluchuan.core.utils.ValidationUtils;
import com.hnluchuan.utils.common.JsonUtils;
import com.hnluchuan.utils.exception.BizException;
import com.hnluchuan.staff.security.PasswordEncoder;
import com.hnluchuan.staff.service.EmailService;
import com.hnluchuan.staff.service.ImageService;
import com.hnluchuan.staff.service.SystemConfigService;

public class CoreBaseController {

    protected  Logger logger = Logger.getLogger(getClass());
   
    @Autowired
    protected PasswordEncoder passwordEncoder;
    @Autowired
    protected EmailService emailService;
    
    @Autowired
    protected ImageService imageService;
   
	@Autowired
	protected SystemConfigService systemConfigService;
	
	protected String ok() {
		JSONObject json = new JSONObject();
		json.put("statusCode", 200);
		json.put("message", "操作成功");
		json.put("success", true);
		json.put("msg", "操作成功");
		return json.toJSONString();
    }
    
    protected String toJson(Object obj) {
		return JsonUtils.toJson(obj);
	}

    protected String saveFileAndReturnFileName(HttpServletRequest request, String formInputName) throws IOException {
        MultipartHttpServletRequest mhs = (MultipartHttpServletRequest) request;  
        MultipartFile mf = mhs.getFile(formInputName);
        String fileName = mf.getOriginalFilename(); 
        if (mf != null && !mf.isEmpty()) {
            return imageService.saveImage(fileName, mf.getBytes());
        }
        return fileName;
    }
    
    protected String saveFilesAndReturnFileName(HttpServletRequest request) throws IOException {
    	String ret = "";
        MultipartHttpServletRequest mhs = (MultipartHttpServletRequest) request;  
        Map<String, MultipartFile> fileMap = mhs.getFileMap();
        for (String key : fileMap.keySet()) {
        	MultipartFile mf = fileMap.get(key);
        	if (mf != null && !mf.isEmpty()) {
        		String url = imageService.saveImage(mf.getOriginalFilename(), mf.getBytes());
        		if (StringUtils.isNotBlank(ret)) {
        			ret = ret + ",";
        		}
        		ret += url;
        	}
        }
        return ret;
    }
    
    protected String saveFileAndReturnFileNameForAdmin(HttpServletRequest request, String formInputName, String filePath, String userFileName) throws IOException {
        MultipartHttpServletRequest mhs = (MultipartHttpServletRequest) request;  
        MultipartFile mf = mhs.getFile(formInputName);
        
        
        String fileName = userFileName; 
        if (mf != null && !mf.isEmpty()) {
            if (StringUtils.isBlank(fileName)) { // 没有指定文件名就生成
                fileName = mf.getOriginalFilename();
                System.out.println("original file name: " + fileName);
                fileName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
            }
            File path = new File(filePath);
            if (!path.exists()) {
                path.mkdirs();
            }
            File file = new File(filePath + "/" + fileName);
            if (!file.exists()) {
                mf.transferTo(file);
            } else {
                throw new BizException("该文件已经存在");
            }
        }
        return fileName;
    }
    
    protected void requiredString(String str, String msg) {
        ValidationUtils.assertTrue(StringUtils.isNotBlank(str), msg);
    }
    
    protected void required(Object obj, String msg) {
        ValidationUtils.assertTrue(obj != null, msg);
    }
    
    protected String formatDate(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }
    
    protected String wsAdminIndex() {
        return "wsadmin/wsadmin_index";
    }
    
    protected String getCtx() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        return (String) requestAttributes.getAttribute("ctx", RequestAttributes.SCOPE_SESSION);
    }
    
    
    protected void assertTrue(Boolean bool, String msg) {
        ValidationUtils.assertTrue(bool, msg);
    }
}
