package club.yuit.ssh.encryption;

import javax.crypto.SecretKey;

/**
 * @author yuit
 * date 2021-03-18 21:15
 **/
public interface AES {
    byte[] encode(byte[] data, SecretKey key);
    byte[] decode(byte[] data,SecretKey key);
}
