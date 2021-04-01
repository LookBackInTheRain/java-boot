package club.yuit.ssh.encryption;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author yuit
 * date 2021-03-18 20:50
 **/
public class AESUtil {

    public static byte[] encode(String rule,byte[] data,  int len, SecretKey secretKey) {

        Cipher cipher = null;
        try {
            cipher = cipher(rule, Cipher.ENCRYPT_MODE, secretKey);
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static byte[] decode(String rule,byte[] data,  int len, SecretKey secretKey)  {
        Cipher cipher = null;
        try {
            cipher = cipher(rule, Cipher.DECRYPT_MODE, secretKey);
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static Cipher cipher(String rule, int mode,  SecretKey secretKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {

        Cipher cipher = Cipher.getInstance(rule);
        cipher.init(mode,secretKey,  new IvParameterSpec(new byte[16]));
        return cipher;
    }

    public static SecretKey  ketGenerator(byte[] seed, int length) throws NoSuchAlgorithmException {
        KeyGenerator  keygen = KeyGenerator.getInstance("AES");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        if (seed!=null){
            random.setSeed(seed);
        }
        keygen.init(length,random);
        SecretKey originalKey  = keygen.generateKey();
        byte[] raw = originalKey.getEncoded();
        SecretKeySpec spec = new SecretKeySpec(raw,"AES");
        return spec;
    }

}
