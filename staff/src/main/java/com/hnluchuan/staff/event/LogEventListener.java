package com.hnluchuan.staff.event;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hnluchuan.staff.dto.SystemLogDTO;
import com.hnluchuan.staff.service.SystemLogService;

@Component
public class LogEventListener implements ApplicationListener<LogEvent> {
    private static Logger logger = Logger.getLogger(LogEventListener.class);
    
    @Autowired
    private SystemLogService systemLogService;
    
    @Override
    @Transactional
    @Async
    public void onApplicationEvent(LogEvent event) {
        systemLogService.create((SystemLogDTO) event.getSource());
        if (logger.isDebugEnabled()) {
            logger.debug("created system log");
        }
    }

}
