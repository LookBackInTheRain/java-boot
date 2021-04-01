package club.yuit.ssh.encryption;

/**
 * @author yuit
 * date 2021-03-18 21:23
 **/
public class AES192CBC extends AbstractAES {
    private final static String RULE = "AES/CBC/PKCS5Padding";
    private final static int LENGTH = 192;
    private final static  AES192CBC CBC =  new AES192CBC();

    private AES192CBC(){
        super(RULE,LENGTH);
    }


    public static AES192CBC getAES(){
        return CBC;
    }


}
