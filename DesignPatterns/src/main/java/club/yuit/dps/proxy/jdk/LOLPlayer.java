package club.yuit.dps.proxy.jdk;

/**
 * @author yuit
 * @date 2021/2/25
 */
public class LOLPlayer implements IPlayer {

    @Override
    public void playGame() {
        System.out.println("LOLPlayer play LOL");
    }
}
