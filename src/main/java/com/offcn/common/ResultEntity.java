package com.offcn.common;

import java.util.HashMap;
import java.util.Map;

public class ResultEntity {//返回的结果

    public Map<String,Object> map = new HashMap<String,Object>();

    public ResultEntity put(String key,Object object){
        this.map.put(key,object);//this表示谁调用这个方法this就表示是谁
        return this;
    }

    public static ResultEntity success(){
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.map.put("statusCode",200);
        resultEntity.map.put("message","响应成功");
        return resultEntity;
    }
    public static ResultEntity error(){
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.map.put("statusCode",500);
        resultEntity.map.put("message","响应失败，服务端异常");
        return resultEntity;
    }

}
