package com.yz.common;

public interface HelloService {
    /**
     * 无返回值
     *
     * @param name
     */
    public void sayHello(String name);

    /**
     * 有返回值
     *
     * @param name
     * @return
     */
    public String sayHelloTo(String name);
}
