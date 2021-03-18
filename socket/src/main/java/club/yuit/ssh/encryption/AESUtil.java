package club.yuit.ssh.encryption;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author yuit
 * date 2021-03-18 20:50
 **/
public class AESUtil {

    public static byte[] encode(String rule,byte[] data,  int len) {

        Cipher cipher = null;
        try {
            cipher = cipher(rule,len, Cipher.ENCRYPT_MODE);
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static byte[] decode(String rule,byte[] data,  int len)  {
        Cipher cipher = null;
        try {
            cipher = cipher(rule,len, Cipher.DECRYPT_MODE);
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static Cipher cipher(String rule, int len, int mode) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        KeyGenerator  keygen = KeyGenerator.getInstance("AES");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(rule.getBytes());
        keygen.init(len,random);

        SecretKey originalKey  = keygen.generateKey();

        byte[] raw = originalKey.getEncoded();

        SecretKey key = new SecretKeySpec(raw,"AES");

        Cipher cipher = Cipher.getInstance("AES");

        cipher.init(mode,key);

        return cipher;
    }

}
