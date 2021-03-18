package club.yuit.ssh.encryption;

/**
 * @author yuit
 * date 2021-03-18 21:23
 **/
public class AES256CBC extends AbstractAES {
    private final static String RULE = "AES/CBC/NoPadding";
    private final static int LENGTH = 256;
    private final static AES256CBC CBC =  new AES256CBC();

    private AES256CBC(){
        super(RULE,LENGTH);
    }


    public static AES256CBC getAES(){
        return CBC;
    }


}
