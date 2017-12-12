package org.smart4j.chapter1.bean;

/**
 * 返回数据对象
 */
public class Data {

    /**
     * 模型数据
     */
    private Object model;

    public Data(Object model){
        this.model = model;
    }

    /**
     * 获取数据
     */
    public Object getModel() {
        return model;
    }
}
