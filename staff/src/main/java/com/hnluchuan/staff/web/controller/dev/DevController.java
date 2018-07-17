package com.hnluchuan.staff.web.controller.dev;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnluchuan.utils.exception.BizException;
import com.hnluchuan.staff.web.controller.BaseController;

@Controller
@RequestMapping(value = "/dev", produces="text/html;charset=UTF-8")
public class DevController extends BaseController {
	
	@RequestMapping("login")
	public String login() throws Exception {
		return "dev/dev_login";
	}
	
	@RequestMapping("index")
	public String index() throws Exception {
		return "dev/dev_index";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("log")
	public String log(Map<String, Object> map) throws Exception {
		Enumeration<Appender> appenders = Logger.getRootLogger().getAllAppenders();
		File dir = null;
		while (appenders.hasMoreElements()) {
			Appender a = appenders.nextElement();
			if (a instanceof FileAppender) {
				String file = ((FileAppender) a).getFile();
				dir = new File(file).getParentFile();
				break;
			}
		}
		map.put("files", dir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".log");
			}
		}));
		return "dev/dev_log";
	}
	
	@RequestMapping("downloadLog")
	@SuppressWarnings("unchecked")
	public ResponseEntity<byte[]> downloadLog(String file) throws Exception {
		File dir = null;
		Enumeration<Appender> appenders = Logger.getRootLogger().getAllAppenders();
		while (appenders.hasMoreElements()) {
			Appender a = appenders.nextElement();
			if (a instanceof FileAppender) {
				dir = new File(((FileAppender) a).getFile()).getParentFile();
				break;
			}
		}
		File dirOfRequest = new File(dir, file).getParentFile();
		if (!dirOfRequest.equals(dir)) {
			throw new BizException("文件不存在");
		}
		
		File targetFile = new File(new File(dir, file).getPath() + ".zip");
		zipFiles(new File(dir, file), targetFile);
		HttpHeaders headers = new HttpHeaders();    
        String fileName=new String((targetFile.getName() + "").getBytes("UTF-8"), "iso-8859-1");  
        headers.setContentDispositionFormData("attachment", fileName);   
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);   
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(targetFile), headers, HttpStatus.CREATED);
	}
	
	private void zipFiles(File sourceFile, File targetFile) throws IOException {
	        FileOutputStream fos = new FileOutputStream(targetFile);
	        ZipOutputStream zipOut = new ZipOutputStream(fos);
	        FileInputStream fis = new FileInputStream(sourceFile);
	        ZipEntry zipEntry = new ZipEntry(sourceFile.getName());
	        zipOut.putNextEntry(zipEntry);
	        final byte[] bytes = new byte[1024];
	        int length;
	        while((length = fis.read(bytes)) >= 0) {
	            zipOut.write(bytes, 0, length);
	        }
	        zipOut.close();
	        fis.close();
	        fos.close();
    }
	
	@RequestMapping("success")
	public String success(Map<String, Object> map) throws Exception {
		map.put("msg", "操作成功");
		return "common/success";
	}
	
	@RequestMapping("fail")
	public String fail() throws Exception {
		return "common/fail";
	}
	
	@ResponseBody
	@RequestMapping("hi")
	@PreAuthorize("permitAll")
	public String hi(HttpServletRequest request) throws Exception {
		request.login("admin", "333333");
		System.out.println("hi...");
		return ok();
	}
}	


