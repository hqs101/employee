package com.hnluchuan.staff.dto;

import java.util.ArrayList;
import java.util.List;

public class WxResponseDTO {
	
	private String ToUserName;
	private String FromUserName;
	private int CreateTime;
	private String MsgType;
	private String Content;
	private int ArticleCount;
	private List<ArticleDTO> Articles = new ArrayList<ArticleDTO>();
	
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	
	public int getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(int createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public int getArticleCount() {
		return ArticleCount;
	}
	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}
	public List<ArticleDTO> getArticles() {
		return Articles;
	}
	public void setArticles(List<ArticleDTO> articles) {
		Articles = articles;
	}
	
	
}

