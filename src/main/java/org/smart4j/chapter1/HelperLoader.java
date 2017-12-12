package org.smart4j.chapter1;

import org.smart4j.chapter1.helper.BeanHelper;
import org.smart4j.chapter1.helper.ClassHelper;
import org.smart4j.chapter1.helper.ControllerHelper;
import org.smart4j.chapter1.helper.IocHelper;
import org.smart4j.chapter1.util.ClassUtil;

/**
 * 加载相应的Helper类
 */
public final class HelperLoader {

    public static void init(){
        //初始化，加载类
        Class<?>[] classList = {ClassHelper.class, BeanHelper.class, IocHelper.class, ControllerHelper.class};
        for(Class<?> cls : classList){
            ClassUtil.loadClass(cls.getName(),true);
        }
    }





}
