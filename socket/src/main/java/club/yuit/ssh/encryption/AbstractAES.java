package club.yuit.ssh.encryption;

/**
 * @author yuit
 * date 2021-03-18 21:24
 **/
public abstract class AbstractAES implements AES {

    private String rule;
    private int length;

    public AbstractAES(String rule, int length) {
        this.rule = rule;
        this.length = length;
    }

    @Override
    public byte[] encode(byte[] data) {
        return  AESUtil.encode(rule,data,length);
    }

    @Override
    public byte[] decode(byte[] data) {
        return  AESUtil.decode(rule,data,length);
    }



}
