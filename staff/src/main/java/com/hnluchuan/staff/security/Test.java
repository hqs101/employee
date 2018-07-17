package com.hnluchuan.staff.security;

import java.io.IOException;
import java.net.URLEncoder;

public class Test {
	
	public static void main(String[] args) throws IOException {
	    String url = "http://test.groupsmedium.com/weiquan/weixin/activity/csdx2?userId=893";
	    System.out.println(URLEncoder.encode(url, "utf-8"));
	}
    
}
