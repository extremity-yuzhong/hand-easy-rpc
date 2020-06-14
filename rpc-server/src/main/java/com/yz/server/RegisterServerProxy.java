package com.yz.server;

import com.yz.common.IRegisterServer;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

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
