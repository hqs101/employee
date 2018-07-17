package com.hnluchuan.staff.web.controller.admin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hnluchuan.utils.exception.BizException;
import com.hnluchuan.staff.common.SystemConfigType;
import com.hnluchuan.staff.web.controller.CoreBaseController;

/**
 * 以Admin开头的Controller，都是只为管理后台提供服务的
 */
@Controller
@RequestMapping(value = "/admin/image", produces="text/html;charset=UTF-8") // produces是为了解决@ResponseBody返回乱码
public class AdminImageController extends CoreBaseController {

	@RequestMapping(value = "index")
	public String index(HttpServletRequest request, Map<String, Object> map) throws Exception {
	    String imagePath = systemConfigService.get(SystemConfigType.ImagePath);
	    File path = new File(imagePath);
	    List<File> files = getFiles(path);
	    
	    List<String> names = new ArrayList<String>();
	    for (File file : files) {
	        String name = file.getPath().replace(imagePath, "");
	        names.add(name);
        }
	    map.put("names", names);
	    return "admin/image/image_index";
	}
	
	private List<File> getFiles(File path) {
	    List<File> list = new ArrayList<File>();
	    if (path.isFile()) {
	        list.add(path);
	        return list;
	    }
	    
	    File[] files = path.listFiles();
	    if (files != null) {
		    for (File file : files) {
	            if (file.isFile()) {
	                list.add(file);
	            } else {
	                list.addAll(getFiles(file));
	            }
	        }
	    }
	    
        return list;
    }

    @RequestMapping(value = "upload", method = RequestMethod.GET)
    public String upload(HttpServletRequest request, Map<String, Object> map) throws Exception {
	    String imagePath = systemConfigService.get(SystemConfigType.ImagePath);
	    map.put("baseImagePath", imagePath);
	    map.put("filePath", imagePath);
	    return "admin/image/image_upload";
	}
	
	@RequestMapping(value = "upload", method = RequestMethod.POST)
    public String uploadSubmit(String filePath, String fileName, HttpServletRequest request, Map<String, Object> map) throws Exception {
	    String baseImagePath = systemConfigService.get(SystemConfigType.ImagePath);
	    if (StringUtils.isBlank(filePath)) {
	        filePath = baseImagePath;
	    }
	    
	    if (!filePath.toLowerCase().startsWith(baseImagePath)
	         && !filePath.equalsIgnoreCase(baseImagePath)) {
	        throw new BizException("路径不合法：" + filePath);
	    }
	    // 这里返回的只是name
	    String name = saveFileAndReturnFileNameForAdmin(request, "file", filePath, fileName);
	    if (!filePath.equalsIgnoreCase(baseImagePath)) {
	        name = filePath.replace(baseImagePath, "") + "/" + name;
	    }
	    
        map.put("baseImagePath", baseImagePath);
        map.put("name", name);
        if (StringUtils.isBlank(filePath)) {
            map.put("filePath", baseImagePath);
        } else {
            map.put("filePath", filePath);
        }
        return "admin/image/image_upload";
    }
	
}