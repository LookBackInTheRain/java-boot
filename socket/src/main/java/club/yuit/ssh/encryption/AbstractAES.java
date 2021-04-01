package club.yuit.ssh.encryption;

import javax.crypto.SecretKey;

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
    public byte[] encode(byte[] data, SecretKey key) {
        return  AESUtil.encode(rule,data,length, key);
    }

    @Override
    public byte[] decode(byte[] data,SecretKey key) {
        return  AESUtil.decode(rule,data,length, key);
    }



}
