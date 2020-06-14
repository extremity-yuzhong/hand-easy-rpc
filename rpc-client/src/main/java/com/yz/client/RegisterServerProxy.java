package com.yz.client;

import com.yz.common.IRegisterServer;

import java.lang.reflect.Proxy;

/**
 * TODO
 *
 * @author yuzhong
 * @version 1.0
 * @date 2020/6/14 9:38
 */
public class RegisterServerProxy {

    public static Object registerServerRpc() {
        return Proxy.newProxyInstance(
                IRegisterServer.class.getClassLoader(),
                new Class[]{IRegisterServer.class},
                new RegisterProxy());
    }
}
