package com.hnluchuan.staff.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hnluchuan.utils.common.MyWebUtils;
import com.hnluchuan.utils.common.WebParamUtils;
import com.hnluchuan.staff.common.SystemConfigType;
import com.hnluchuan.staff.service.ImageService;

@Controller
@RequestMapping(value = "/image", produces="text/html;charset=UTF-8")
public class ImageController extends CoreBaseController {
	
	private static final Logger logger = Logger.getLogger(ImageController.class);

	@ResponseBody
    @RequestMapping(value = "upload7")
    public String upload7(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return saveFileAndReturnFileName(request, "image");
	}
	
	@ResponseBody
	@RequestMapping(value = "sm")
	public String sm(HttpServletRequest request) {
		logger.info("smmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");
		String url = "";
		 MultipartHttpServletRequest mhs = (MultipartHttpServletRequest) request;  
	        Map<String, MultipartFile> fileMap = mhs.getFileMap();
	        for (String key : fileMap.keySet()) {
	        	MultipartFile mf = fileMap.get(key);
	        	if (mf != null && !mf.isEmpty()) {
					try {
						url = imageService.saveImage(mf.getOriginalFilename(), mf.getBytes());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        	}
	        }
		
		return url;
	}
	
    @ResponseBody
    @RequestMapping(value = "upload")
    public String upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        // 获得磁盘文件条目工厂。
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 获取文件上传需要保存的路径，upload文件夹需存在。
        String path = request.getSession().getServletContext().getRealPath("/upload");
        // 设置暂时存放文件的存储室，这个存储室可以和最终存储文件的文件夹不同。因为当文件很大的话会占用过多内存所以设置存储室。
        factory.setRepository(new File(path));
        // 设置缓存的大小，当上传文件的容量超过缓存时，就放到暂时存储室。
        factory.setSizeThreshold(1024 * 1024);
        // 上传处理工具类（高水平API上传处理？）
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            // 调用 parseRequest（request）方法 获得上传文件 FileItem 的集合list 可实现多文件上传。
            @SuppressWarnings("unchecked")
            List<FileItem> list = (List<FileItem>) upload.parseRequest(request);
            for (FileItem item : list) {
                // 获取表单属性名字。
                String name = item.getFieldName();
                // 如果获取的表单信息是普通的文本信息。即通过页面表单形式传递来的字符串。
                if (item.isFormField()) {
                    // 获取用户具体输入的字符串，
                    String value = item.getString();
                    request.setAttribute(name, value);
                }
                // 如果传入的是非简单字符串，而是图片，音频，视频等二进制文件。
                else {
                    // 获取路径名
                    String value = item.getName();
                    // 取到最后一个反斜杠。
                    int start = value.lastIndexOf("\\");
                    // 截取上传文件的 字符串名字。+1是去掉反斜杠。
                    String filename = value.substring(start + 1);
                    request.setAttribute(name, filename);

                    /*
                     * 第三方提供的方法直接写到文件中。 item.write(new File(path,filename));
                     */
                    // 收到写到接收的文件中。
                    OutputStream out = new FileOutputStream(new File(path, filename));
                    InputStream in = item.getInputStream();

                    int length = 0;
                    byte[] buf = new byte[1024];
                    System.out.println("获取文件总量的容量:" + item.getSize());

                    while ((length = in.read(buf)) != -1) {
                        out.write(buf, 0, length);
                    }
                    in.close();
                    out.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }
    
    @ResponseBody
    @RequestMapping(value = "upload2")
    public String upload2(HttpServletRequest request) throws Exception {
        MultipartHttpServletRequest mhs = (MultipartHttpServletRequest) request;  
        MultipartFile mf = mhs.getFile("file");  
        String fileName = mf.getOriginalFilename();
        fileName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
        File file = new File(systemConfigService.get(SystemConfigType.ImagePath) + "/" + fileName);
        mf.transferTo(file);
        String msg = "{\"err\":\"\",\"msg\":\"" + request.getSession().getAttribute("ctx").toString() + "/image/" + fileName + "\"}";
        return msg;
    }
    
	@ResponseBody
	@RequestMapping(value = "{name}.{suffix}", produces="image/*")
	public void image(HttpServletRequest request, HttpServletResponse response, @PathVariable("name") String name, @PathVariable("suffix") String suffix) throws IOException {
		String imageName = name + "." + suffix;
		imageName = WebParamUtils.getUTF8Param(imageName);
		File file = new File(imageService.getImagePath(), imageName);
		if (!file.exists()) {
		    return;
		}
		
		Integer width = getImageWidth(request);
		if (width != null && width > 0) {
		    String path = ImageService.getOrCreateSmallImage(file.getPath(), width);
		    file = new File(path);
		}
		
		response.setContentType("image/jpeg"); // 设置返回的文件类型
		ServletOutputStream outStream = response.getOutputStream();// 得到向客户端输出二进制数据的对象  
        FileInputStream fis = new FileInputStream(file); // 以byte流的方式打开文件  
        // 读数据  
        byte data[] = new byte[1000];  
        while (fis.read(data) > 0) {  
            outStream.write(data);  
        }  
        fis.close();
          
        outStream.write(data); // 输出数据  
        outStream.close();  
		
	}

    private Integer getImageWidth(HttpServletRequest request) {
        Integer width = ServletRequestUtils.getIntParameter(request, "width", 0);
        if (MyWebUtils.isFromMobile()) {
        }
        return width;
    }
	
}
