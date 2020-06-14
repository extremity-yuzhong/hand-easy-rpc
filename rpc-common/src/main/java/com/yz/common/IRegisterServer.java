package com.yz.common;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author yuzhong
 * @version 1.0
 * @date 2020/6/14 9:10
 */
public interface IRegisterServer {
    public static final Map<String, URL> RES_LIST = new HashMap<String, URL>();

    /**
     * 添加注册
     *
     * @param className
     * @param url
     */
    public void add(String className, URL url);

    /**
     * 获取注册
     *
     * @return
     */
    public URL get(String className);

}
