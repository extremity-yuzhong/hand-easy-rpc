package com.yz.register;

import com.yz.common.IRegisterServer;
import com.yz.common.URL;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author yuzhong
 * @version 1.0
 * @date 2020/6/14 9:24
 */
public class RegisterServerImpl implements IRegisterServer {


    @Override
    public void add(String className, URL url) {
        RES_LIST.put(className, url);
    }

    @Override
    public URL get(String className) {
        return RES_LIST.get(className);
    }
}
