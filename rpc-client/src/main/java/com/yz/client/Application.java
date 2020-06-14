package com.yz.client;

import com.yz.common.HelloService;

/**
 * TODO
 *
 * @author yuzhong
 * @version 1.0
 * @date 2020/6/14 9:52
 */
public class Application {
    public static void main(String[] args) throws Exception {

        HelloService helloService = (HelloService) ProxyFactory.getProxyRpc(HelloService.class.getName());
        helloService.sayHello("yuzhong");
        System.out.println(helloService.sayHelloTo("yuzhong"));
    }
}
