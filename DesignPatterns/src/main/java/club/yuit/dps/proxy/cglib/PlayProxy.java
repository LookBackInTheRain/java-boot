package club.yuit.dps.proxy.cglib;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author yuit
 * @date 2021/3/9
 */
public class PlayProxy implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Logger log = LoggerFactory.getLogger(o.getClass().getName());
        log.info("------------>before");
        Annotation[] annotations = method.getAnnotations();
        methodProxy.invokeSuper(o,objects);
        log.info("------------>after");
        return o;
    }
}
