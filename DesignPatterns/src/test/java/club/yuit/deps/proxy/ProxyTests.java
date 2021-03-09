package club.yuit.deps.proxy;

import club.yuit.dps.proxy.cglib.PlayProxy;
import club.yuit.dps.proxy.jdk.IPlayer;
import club.yuit.dps.proxy.jdk.InvocationHandlerImpl;
import club.yuit.dps.proxy.jdk.LOLPlayer;
import net.sf.cglib.proxy.Enhancer;
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
    public void jdkProxyTest(){
        IPlayer player = new LOLPlayer();
        Class<?> clazz = player.getClass();
        IPlayer proxy  = (IPlayer)Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),new InvocationHandlerImpl(player));

        proxy.playGame();

        System.out.println();

    }

    @Test
    public void cglibProxyTest(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(LOLPlayer.class);
        enhancer.setCallback(new PlayProxy());

        LOLPlayer player = (LOLPlayer) enhancer.create();

        player.playGame();
    }

}


