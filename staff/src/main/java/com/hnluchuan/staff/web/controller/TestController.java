package com.hnluchuan.staff.web.controller;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnluchuan.example.dao.ExampleUserDAO;
import com.hnluchuan.example.event.ExampleUserCreatedEvent;
import com.hnluchuan.example.model.ExampleUser;
import com.hnluchuan.utils.exception.BizException;

@Controller
@RequestMapping(value = "/test", produces="text/html;charset=UTF-8")
public class TestController extends BaseController {
	
	@Autowired
	private ExampleUserDAO exampleUserDAO;
	@Autowired
	private ApplicationContext applicationContext;
	
	@ResponseBody
	@RequestMapping("ab")
	@Transactional
	public String ab() throws Exception {
		ExampleUser user = new ExampleUser();
		user.setUsername("kevin" + System.currentTimeMillis());
		Long id = exampleUserDAO.create(user);
		applicationContext.publishEvent(new ExampleUserCreatedEvent(id));
		return ok();
	}
	
	
	@RequestMapping("log")
	public String log(Map<String, Object> map) throws Exception {
		if (1 == 1) {
			long start = System.currentTimeMillis();
			try {
				String a = null;
				a.toCharArray();
			} catch (Exception e) {
				logger.error(e, e);
			}
			System.out.println("took " + (System.currentTimeMillis() - start) + "ms");
			throw new BizException("xxx");
		}
		Enumeration<Logger> enums = LogManager.getCurrentLoggers();
		while (enums.hasMoreElements()) {
			Logger logger = enums.nextElement();
			if (!logger.getName().startsWith("com.hnluchuan")) {
				continue;
			}
		}
		
		Enumeration<Appender> appenders = Logger.getRootLogger().getAllAppenders();
		while (appenders.hasMoreElements()) {
			Appender a = appenders.nextElement();
			if (a instanceof FileAppender) {
				String file = ((FileAppender) a).getFile();
				System.out.println(file);
			} else {
				System.out.println("not");
			}
		}
		return "log/log_index";
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


