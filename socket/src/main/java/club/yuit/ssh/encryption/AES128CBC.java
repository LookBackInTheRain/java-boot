package club.yuit.ssh.encryption;

/**
 * @author yuit
 * date 2021-03-18 20:49
 **/
public class AES128CBC extends AbstractAES {

    private final static String RULE = "AES/CBC/NoPadding";
    private final static int LENGTH = 128;
    private final static  AES128CBC CBC =  new AES128CBC();

    private AES128CBC(){
        super(RULE,LENGTH);
    }


    public static AES128CBC getAES(){
        return CBC;
    }

}
