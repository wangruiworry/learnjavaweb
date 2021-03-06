package org.smart4j.chapter1.bean;

import org.smart4j.chapter1.util.CastUtil;

import java.util.Map;

/**
 * 请求参数对象
 */
public class Param {

    private Map<String, Object> paramMap;

    /**
     * 构造函数
     * @param paramMap
     */
    public Param(Map<String, Object> paramMap){
        this.paramMap = paramMap;
    }

    /**
     * 根据参数名获取long型参数值
     */
    public long getLong(String name){
        return CastUtil.castLong(paramMap.get(name));
    }

    /**
     * 获取所有字段信息
     */
    public Map<String, Object> getParamMap(){
        return paramMap;
    }



}
