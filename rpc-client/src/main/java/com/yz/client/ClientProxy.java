package com.yz.client;

import com.yz.common.URL;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * TODO
 *
 * @author Administrator
 * @version 1.0
 * @date 2020/6/14 12:12
 */
public class ClientProxy implements InvocationHandler {
    private URL url;
    private String className;

    public ClientProxy(String className, URL url) {
        this.url = url;
        this.className = className;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //1.建立远程连接
        Socket socket = new Socket(url.getHostname(), url.getPort());
        //2.要调用的类、方法、参数
        String methodName = method.getName();
        //为了鉴别方法的重载，这里需要传入参数类型
        Class[] paramTypes = method.getParameterTypes();
        //3.传输类信息，请求远程执行结果
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.writeObject(className);
        outputStream.writeObject(methodName);
        outputStream.writeObject(paramTypes);
        outputStream.writeObject(args);
        outputStream.flush();

        //4.接收返回的结果
        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
        Object object = inputStream.readObject();
        inputStream.close();
        outputStream.close();

        socket.close();
        return object;
    }
}
