package club.yuit.dps.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author yuit
 * @date 2021/2/25
 */
@Slf4j
public class InvocationHandlerImpl implements InvocationHandler {

    /**
     * 被代理对象
     */
    private Object obj;

    public InvocationHandlerImpl(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before invoke");
        log.info("-----------------------");
        Object p=method.invoke(this.obj);
        System.out.println("after invoke");
        return p;
    }




}
