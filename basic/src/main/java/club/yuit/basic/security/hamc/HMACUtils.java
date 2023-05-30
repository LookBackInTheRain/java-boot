package club.yuit.basic.security.hamc;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.digest.HMac;
import cn.hutool.crypto.digest.HmacAlgorithm;
import cn.hutool.crypto.symmetric.AES;

import javax.crypto.Mac;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author yuit
 * @date 2022/5/12
 **/
public class HMACUtils {

    // SHA-256 分组长度 64 字节
    public final static int SHA256_GROUP_LENGTH = 512 / 8;

    private final static byte[] IPAD = new byte[SHA256_GROUP_LENGTH];
    private static final byte[] OPAD = new byte[SHA256_GROUP_LENGTH];

    static {
        for (int i = 0; i < SHA256_GROUP_LENGTH; i++) {
            IPAD[i] = 0x36;
            OPAD[i] = 0x5C;
        }
    }

    /**
     * HMAC-SHA-256
     *
     * @param text   文本
     * @param secret 秘钥
     * @return 摘要字符串（16进制）
     * @throws NoSuchAlgorithmException
     */
    public static String HMAC256(String text, String secret) throws NoSuchAlgorithmException {

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] secretBytes = secret.getBytes(StandardCharsets.UTF_8);

        if (secretBytes.length > SHA256_GROUP_LENGTH) {
            secretBytes = digest.digest(secretBytes);
        }



        if (secretBytes.length < SHA256_GROUP_LENGTH) {
            secretBytes = afterAppend(secretBytes, SHA256_GROUP_LENGTH - secretBytes.length);
        }


        byte[] ipadKey = xor(secretBytes, IPAD);


        byte[] textBts = text.getBytes(StandardCharsets.UTF_8);

        byte[] cp1Bts = new byte[ipadKey.length + textBts.length];
        System.arraycopy(ipadKey, 0, cp1Bts, 0, ipadKey.length);
        System.arraycopy(textBts, 0, cp1Bts, ipadKey.length, textBts.length);

        // 第一次摘要
        cp1Bts = digest.digest(cp1Bts);

        byte[] opadKey = xor(secretBytes, OPAD);

        byte[] cp2Bts = new byte[opadKey.length + cp1Bts.length];

        System.arraycopy(opadKey, 0, cp2Bts, 0, opadKey.length);
        System.arraycopy(cp1Bts, 0, cp2Bts, opadKey.length, cp1Bts.length);

        cp2Bts = digest.digest(cp2Bts);

        return toHex(cp2Bts);
    }

    private static String toHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            if (b < 10 && b > 0) {
                builder.append("0");
                builder.append(b);
            } else {
                builder.append(String.format("%x", b));
            }

        }

        return builder.toString();
    }


    /**
     * 字节数组异或云素昂
     * @param v1
     * @param v2
     * @return
     */
    private static byte[] xor(byte[] v1, byte[] v2) {

        if (v1.length!=v2.length){
            throw new RuntimeException("两个数组长度不相等");
        }

        byte[] result = new byte[v1.length];
        for (int i = 0; i < v1.length; i++) {
            result[i] = (byte) (v1[i] ^ v2[i]);
        }

        return result;
    }

    private static byte[] beforeAppend(byte[] params, int length) {
        byte[] tmp = new byte[params.length + length];
        System.arraycopy(params, 0, tmp, length, params.length);
        return tmp;
    }

    private static byte[] afterAppend(byte[] params, int length) {
        byte[] tmp = new byte[params.length + length];
        System.arraycopy(params, 0, tmp, 0, params.length);
        return tmp;
    }


    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        String text = "abc1231231231";
        String secret = "123qwe12377777777777777777772727272727272727272727272718237128371283172938712931723912731923719237129712937129371239172391273912739123719237129371923719237129346198jhasdgsdhfgsdfvsdfg4278346gdfht782783weygshudfgfygsyudfgshdfvsdfgsydftswydfgsdhfgsdhfgsdhjfsgdjfsgfjsgfjsgfsjdfgsdjfg";

        String hmacTxt = HMAC256(text, secret);
        System.out.println(hmacTxt);

        HMac mac = new HMac(HmacAlgorithm.HmacSHA256, secret.getBytes(StandardCharsets.UTF_8));

        AES aes = new AES(Mode.CBC, Padding.PKCS5Padding);

        System.out.println(mac.digestHex(text));



    }


}
