package com.hnluchuan.staff.aop;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.hnluchuan.utils.common.MyWebUtils;

@Aspect
@Component
@Order(3)
public class JsonpControllerAOP extends BaseControllerAOP {
    private static Logger logger = Logger.getLogger(JsonpControllerAOP.class);

    public static final String EDP = "execution(* com.hnluchuan.*.*.controller.m..*.*(..))";

    public JsonpControllerAOP() {
    }
    
    @Around("pointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object obj = joinPoint.proceed();
        HttpServletRequest request = MyWebUtils.getCurrentRequest();
        if (request == null) {
            return obj;
        }
        String callback = request.getParameter("callback");
        if (StringUtils.isBlank(callback)) {
            return obj;
        }
        return callback + "(" + obj.toString() + ");";
    }
    
    @Pointcut(EDP)
    public void pointcut() {
    }

    @Before("pointcut()")
    public void logBefore() {
    }

    @After("pointcut()")
    public void logAfter() {
    }

}
