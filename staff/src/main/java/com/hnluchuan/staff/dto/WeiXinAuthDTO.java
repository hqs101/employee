package com.hnluchuan.staff.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

public class WeiXinAuthDTO {
    private static Logger logger = Logger.getLogger(WeiXinAuthDTO.class);
    
    private String openid;
    private String unionid;
    private String nickName;
    private String headImage;
    private Integer sex;
    private boolean success;
    private String errorMessage;
    
    public String getOpenid() {
        return openid;
    }
    public void setOpenid(String openid) {
        this.openid = openid;
    }
    public String getUnionid() {
        return unionid;
    }
    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getHeadImage() {
        return headImage;
    }
    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }
    public Integer getSex() {
        return sex;
    }
    public void setSex(Integer sex) {
        this.sex = sex;
    }
    
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    
    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    public static WeiXinAuthDTO fromJson(JSONObject jsonObj) {
        if (logger.isDebugEnabled()) {
            logger.debug("微信授权结果：" + jsonObj);
        }
        WeiXinAuthDTO dto = new WeiXinAuthDTO();
        
        if (jsonObj.containsKey("errcode")) {
            // throw new BizException(jsonObj.get("errmsg").toString());
            dto.setSuccess(false);
            dto.setErrorMessage(jsonObj.get("errmsg").toString());
            return dto;
        }
        
        dto.setSuccess(true);
        
        String openid = jsonObj.getString("openid");
        String unionid = jsonObj.getString("unionid");
        String nickName = jsonObj.getString("nickname");
        String headImage = jsonObj.getString("headimgurl");
        int sex = jsonObj.getIntValue("sex") - 1; // 转成系统的性别标识
        
        dto.setOpenid(openid);
        dto.setUnionid(unionid);
        dto.setNickName(nickName);
        dto.setHeadImage(headImage);
        dto.setSex(sex);
        
        return dto;
    }
    
    public static WeiXinAuthDTO fromUser(UserDTO user) {
    	/*
        WeiXinAuthDTO dto = new WeiXinAuthDTO();
        
        dto.setSuccess(true);
        
        String openid = user.getOpenidPublicAccount();
        String unionid = user.getUnionid();
        String nickName = user.getNickName();
        String headImage = user.getHeadImage();
        int sex = user.getSex();
        
        dto.setOpenid(openid);
        dto.setUnionid(unionid);
        dto.setNickName(nickName);
        dto.setHeadImage(headImage);
        dto.setSex(sex);
        
        return dto; */
    	return null;
    }

    public static WeiXinAuthDTO failed(String errorMessage) {
        WeiXinAuthDTO dto = new WeiXinAuthDTO();
        dto.setSuccess(false);
        dto.setErrorMessage(errorMessage);
        return dto;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
