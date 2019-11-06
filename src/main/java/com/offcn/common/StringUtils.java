package com.offcn.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StringUtils {
    public static String parsepareterMapToString(Map<String, Object> parameterMap) {
        Set<Map.Entry<String, Object>> entries = parameterMap.entrySet();
        String str = "";
        for (Map.Entry<String, Object> entry : entries) {
            String key = entry.getKey();
            String value = (String) entry.getValue();
            str = str+ "&" + "search_" + key + "=" + value;
        }
        return str;
    }

    //解析页面传过来的查询参数
    public static Map<String, String> parseParamterMapToMyBatilsMap(Map<String, Object> parameterMap) {
        Map<String,String> map = new HashMap<String,String>();
        Set<Map.Entry<String, Object>> entries = parameterMap.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            String key = entry.getKey();//就是name=search_status  或者是search_keyward
            String value = (String) entry.getValue();//就是页面传过来的查询字段，条件
            if(key.contains("like")){
                key = key.substring(key.indexOf("_")+1);
                value = "%" + value + "%";
            }
            map.put(key,value);
        }
        return map;
    }
}
