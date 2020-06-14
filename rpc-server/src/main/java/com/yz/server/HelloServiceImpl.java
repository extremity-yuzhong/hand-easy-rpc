package com.yz.server;

import com.yz.common.HelloService;

/**
 * TODO
 *
 * @author yuzhong
 * @version 1.0
 * @date 2020/6/14 9:01
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public void sayHello(String name) {
        System.out.println("hello " + name);
    }

    @Override
    public String sayHelloTo(String name) {
        return "hi," + name;
    }
}
