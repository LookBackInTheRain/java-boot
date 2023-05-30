package club.yuit.basic.reflect;

import club.yuit.basic.reflect.jdk.IProxy;
import club.yuit.basic.reflect.jdk.ProxyImpl;
import club.yuit.basic.reflect.jdk.ProxyInvocationHandler;

import java.lang.reflect.Proxy;
import java.util.ArrayList;

/**
 * @author yuit
 * @date 2022/4/6
 **/
public class REMain {

    public static void main(String[] args) {

        /*ProxyInvocationHandler pxy = new ProxyInvocationHandler(new ProxyImpl());
        IProxy p = (IProxy) Proxy.newProxyInstance(REMain.class.getClassLoader(),new Class[]{IProxy.class},pxy);
        p.sayHello();*/

        System.out.println(4>>1);
        new ArrayList<>();
    }

}


