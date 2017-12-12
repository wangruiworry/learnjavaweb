package org.smart4j.chapter1.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 编码与解码操作工具类
 */
public final class CodeUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(CodeUtil.class);

    /**
     * 将URL编码
     */
    public static String encodeURL(String source){
        String target;
        try{
            target = URLEncoder.encode(source, "UTF-8");
        }catch (Exception e){
            LOGGER.error("encode url failure", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    /**
     * 将URL编码
     */
    public static String decodeURL(String source){
        String target;
        try{
            target = URLDecoder.decode(source, "UTF-8");
        }catch (Exception e){
            LOGGER.error("decode url failure", e);
            throw new RuntimeException(e);
        }
        return target;
    }
}
