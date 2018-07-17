package com.hnluchuan.staff.service;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

@Service
public class QiniuService {
    private static Logger logger = Logger.getLogger(QiniuService.class);
    
    @Value(value = "${qiniu.ak}")
    private String ak;
    @Value(value = "${qiniu.sk}")
    private String sk;
    @Value(value = "${qiniu.domain}")
    private String domain;
    @Value(value = "${qiniu.bucket}")
    private String bucket;

    public String upload(byte[] data) {
    	return upload(data, null);
    }
    
    public String upload(byte[] data, String originalFilename) {
        Auth auth = Auth.create(ak, sk);

        // 简单上传，使用默认策略
        // private String getUpToken0(){
        String upToken = auth.uploadToken(bucket);
        
        UploadManager uploadManager = new UploadManager();

        String key = originalFilename;
        if (StringUtils.isBlank(key)) {
        	key = UUID.randomUUID().toString();
        }
        try {
            Response res = uploadManager.put(data, key, upToken);
            
            JSONObject json = JSONObject.parseObject(res.bodyString());
            String fileName = json.getString("key");
            String url = "http://" + domain + "/" + fileName;
            return url;
        } catch (QiniuException e) {
            logger.error(e, e);
            Response r = e.response;
            try {
                // 响应的文本信息
                logger.error(r.bodyString());
            } catch (QiniuException e1) {
                // ignore
            }
        }
        return null;
    }

    public class MyRet {
        public long fsize;
        public String key;
        public String hash;
        public int width;
        public int height;
    }
    
}
