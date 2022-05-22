package club.yuit.basic.reflect.jdk;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yuit
 * @date 2022/4/6
 **/
@Slf4j
public class ProxyImpl implements IProxy{
    @Override
    public void sayHello() {
        log.info("{}",ProxyImpl.class);
        System.out.println("ProxyImpl say Hello!");
    }
}
