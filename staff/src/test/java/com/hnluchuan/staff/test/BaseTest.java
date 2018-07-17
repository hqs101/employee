package com.hnluchuan.staff.test;

import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@WebAppConfiguration
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
locations = {
   "classpath:META-INF/applicationContext.xml",
   "classpath:META-INF/applicationContext-security.xml",
   "classpath:WEB-INF/main-servlet.xml"
   }
)
public abstract class BaseTest extends BaseDependency {
    protected static Logger logger = Logger.getLogger("test");
    
    protected void bindRequest() {
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));
    }
}
