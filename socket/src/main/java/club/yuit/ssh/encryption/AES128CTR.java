package club.yuit.ssh.encryption;

/**
 * @author yuit
 * date 2021-03-18 21:33
 **/
public class AES128CTR extends AbstractAES {
    private final static String RULE = "AES/CTR/NoPadding";
    private final static int LENGTH = 128;
    private final static AES128CTR CBC =  new AES128CTR();

    private AES128CTR(){
        super(RULE,LENGTH);
    }


    public static AES128CTR getAES(){
        return CBC;
    }

}
