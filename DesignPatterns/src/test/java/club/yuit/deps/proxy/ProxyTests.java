package club.yuit.deps.proxy;

import club.yuit.dps.proxy.IPlayer;
import club.yuit.dps.proxy.InvocationHandlerImpl;
import club.yuit.dps.proxy.LOLPlayer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.lang.reflect.Proxy;

/**
 * @author yuit
 * @date 2021/2/25
 */
@RunWith(JUnit4.class)
public class ProxyTests {

    @Test
    public void proxyTest(){
        IPlayer player = new LOLPlayer();
        Class<?> clazz = player.getClass();
        IPlayer proxy  = (IPlayer)Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),new InvocationHandlerImpl(player));

        proxy.playGame();

        System.out.println();

    }

}
