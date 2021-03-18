package club.yuit.ssh.encryption;

/**
 * @author yuit
 * date 2021-03-18 21:33
 **/
public class AES256CTR extends AbstractAES {
    private final static String RULE = "AES/CTR/NoPadding";
    private final static int LENGTH = 256;
    private final static AES256CTR CBC =  new AES256CTR();

    private AES256CTR(){
        super(RULE,LENGTH);
    }


    public static AES256CTR getAES(){
        return CBC;
    }

}
