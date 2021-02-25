package club.yuit.thread;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {

        Cat c = new Cat();

        Object proxy =  Proxy.newProxyInstance(c.getClass().getClassLoader(), c.getClass().getInterfaces(), new InvokeHandler(c));



        System.out.println();
    }
}

interface ICat {
    void c();
}

class Cat implements ICat {
    @Override
    public void c(){
        System.out.println("Cat");
    }
}

class InvokeHandler implements InvocationHandler {

    Object obj;

    public InvokeHandler(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("before");
        Object v = method.invoke(obj,args);
        System.out.println("after");
        return v;
    }
}
