package com.yz.client;

import com.yz.common.IRegisterServer;
import com.yz.common.URL;

import java.lang.reflect.Proxy;

/**
 * TODO
 *
 * @author yuzhong
 * @version 1.0
 * @date 2020/6/14 9:53
 */
public class ProxyFactory {


    public static Object getProxyRpc(String className) throws Exception {
        //1.去注册中心获取对应要调用的类的调用地址
        IRegisterServer iRegisterServer = (IRegisterServer) RegisterServerProxy.registerServerRpc();
        URL url = iRegisterServer.get(className);
        if (url == null) {
            throw new RuntimeException("无此注册服务，请检查配置!");
        }
        Class<?> clazz = Class.forName(className);
        return Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class[]{clazz}, new ClientProxy(className,url));
    }
}
