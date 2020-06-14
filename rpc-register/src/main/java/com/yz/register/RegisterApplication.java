package com.yz.register;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TODO
 *
 * @author yangyuliang
 * @version 1.0
 * @date 2020/6/14 9:29
 */
public class RegisterApplication {
    public static void main(String[] args) throws Exception {
        //1.Socket绑定本地端口
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("注册中心启动。。。。");
        //2.监听端口
        while (true) {
            Socket socket = null;
            ObjectOutputStream outputStream = null;
            ObjectInputStream inputStream = null;
            try {
                socket = serverSocket.accept();
                //1.接收所有的参数
                inputStream = new ObjectInputStream(socket.getInputStream());
                String methodName = (String) inputStream.readObject();
                Class[] paramTypes = (Class[]) inputStream.readObject();
                Object[] args4Method = (Object[]) inputStream.readObject();
                //2.执行方法
                Class registerServerClass = RegisterServerImpl.class;
                Object o = registerServerClass.newInstance();
                Method method = registerServerClass.getMethod(methodName, paramTypes);
                Object invoke = method.invoke(o, args4Method);
                //3.返回结果
                outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeObject(invoke);
                outputStream.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //4.关闭连接
                outputStream.close();
                inputStream.close();
                socket.close();
            }

        }
    }
}
