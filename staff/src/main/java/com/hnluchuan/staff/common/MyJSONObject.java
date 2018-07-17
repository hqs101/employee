package com.hnluchuan.staff.common;

import com.alibaba.fastjson.JSONObject;

public class MyJSONObject extends JSONObject {
    @Override
    public Object put(String key, Object value) {
        if (value == null) {
            value = "";
        }
        return super.put(key, value);
    }
}
