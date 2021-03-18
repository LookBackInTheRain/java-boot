package club.yuit.ssh.encryption;

/**
 * @author yuit
 * date 2021-03-18 21:15
 **/
public interface AES {
    byte[] encode(byte[] data);
    byte[] decode(byte[] data);
}
