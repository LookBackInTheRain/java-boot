package club.yuit.basic.reflect.jdk;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author yuit
 * @date 2022/4/6
 **/
@Slf4j
public class ProxyInvocationHandler implements InvocationHandler {

    private Object target;

    public ProxyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("{}",proxy.getClass());
        log.info("{}",target.getClass());
        return method.invoke(target,args);
    }

}
