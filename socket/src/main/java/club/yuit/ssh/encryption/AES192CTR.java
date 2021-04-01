package club.yuit.ssh.encryption;

/**
 * @author yuit
 * date 2021-03-18 21:33
 **/
public class AES192CTR extends AbstractAES {
    private final static String RULE = "AES/CTR/PKCS5Padding";
    private final static int LENGTH = 192;
    private final static AES192CTR CBC =  new AES192CTR();

    private AES192CTR(){
        super(RULE,LENGTH);
    }


    public static AES192CTR getAES(){
        return CBC;
    }

}
