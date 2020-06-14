package com.yz.server;

import com.yz.common.HelloService;
import com.yz.common.IRegisterServer;
import com.yz.common.URL;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author yangyuliang
 * @version 1.0
 * @date 2020/6/14 9:29
 */
public class ServerApplication {
    static Map<String,Class> mapper = new HashMap<>();
    static {
        mapper.put(HelloService.class.getName(),HelloServiceImpl.class);
    }
    public static void main(String[] args) throws Exception {
        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles","true");
        //1.注册服务 获取代理类
        IRegisterServer iRegisterServer = (IRegisterServer) RegisterServerProxy.registerServerRpc();
        URL url = new URL("127.0.0.1", 8889);
        iRegisterServer.add(HelloService.class.getName(), url);

        //2.Socket绑定本地端口启动服务
        ServerSocket serverSocket = new ServerSocket(8889);
        System.out.println("服务端启动。。。。");
        //3.监听端口
        while (true) {
            Socket socket = null;
            ObjectOutputStream outputStream = null;
            ObjectInputStream inputStream = null;
            try {
                 socket = serverSocket.accept();
                //1.接收所有的参数
                inputStream = new ObjectInputStream(socket.getInputStream());
                String clszzName = (String) inputStream.readObject();
                String methodName = (String) inputStream.readObject();
                Class[] paramTypes = (Class[]) inputStream.readObject();
                Object[] args4Method = (Object[]) inputStream.readObject();
                //2.执行方法
                Class<?> aClass = mapper.get(clszzName);
                Method method = aClass.getMethod(methodName, paramTypes);
                Object invoke = method.invoke(aClass.newInstance(), args4Method);
                //3.返回结果
                outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeObject(invoke);
                outputStream.flush();

            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                //4.关闭连接
                outputStream.close();
                inputStream.close();
                socket.close();
            }
        }
    }
}
