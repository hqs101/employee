package com.hnluchuan.staff.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hnluchuan.staff.common.SystemConfigType;
import com.hnluchuan.staff.dto.SystemConfigDTO;
import com.hnluchuan.staff.service.SystemConfigService;

@Component
public class SystemConfigCache {
    
    @Autowired
    private SystemConfigService systemConfigService;
    
    private Map<String, String> map = new ConcurrentHashMap<String, String>();
    
    @PostConstruct
    public void init() {
        refresh();
    }
    
    //@Scheduled(fixedDelay = 60000)
    public void refresh() {
        List<SystemConfigDTO> configs = systemConfigService.find(new SystemConfigDTO(), null);
        Map<String, String> tmpMap = new HashMap<String, String>();
        for (SystemConfigDTO dto : configs) {
            tmpMap.put(dto.getKey().toLowerCase(), dto.getValue() == null ? "" : dto.getValue());
        }
        map.putAll(tmpMap);
    }
    
    public String get(SystemConfigType key) {
        return map.get(key.value().toLowerCase());
    }
}
