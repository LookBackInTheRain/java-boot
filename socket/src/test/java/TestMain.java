import club.yuit.ssh.encryption.*;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;


public class TestMain {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        String padding = "AES/CBC/PKCS5Padding";
        String data = "123456789asdasdasdasdasdasdasdasdasd";

        SecretKey key = AESUtil.ketGenerator("ABCHDGDJHJD87234234".getBytes(), 192);
        AES aes = AES256CTR.getAES();
        byte[] res = aes.encode(data.getBytes(), key);
        byte[] decRes = aes.decode(res,key);
        System.out.println("");
    }


    static void decode(byte[] src) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed("AES/CBC/PKCS5Padding".getBytes());
        keygen.init(192, random);

        SecretKey originalKey = keygen.generateKey();

        byte[] raw = originalKey.getEncoded();

        SecretKeySpec spec = new SecretKeySpec(raw, "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        cipher.init(Cipher.DECRYPT_MODE, spec);

        byte[] res = cipher.doFinal(src);

        if (res != null) {
            System.out.println(new String(res));
        }

    }


}

