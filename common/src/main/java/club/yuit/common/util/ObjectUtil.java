package club.yuit.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author yuit
 * @date 2020/5/14 上午11:48
 */
public class ObjectUtil {
    /**
     * object序列化为 byte 数组
     *
     * @param obj
     * @return
     */
    public static byte[] objectToBytes(Object obj) {

        byte[] bytes = null;

        try (ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
             ObjectOutputStream objOut = new ObjectOutputStream(byteOut)
        ) {

            objOut.writeObject(obj);
            objOut.flush();
            bytes = byteOut.toByteArray();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return bytes;
    }


    /**
     * byte 数组反序列化为 object
     *
     * @param bytes
     * @return
     */
    public static <T> T bytesToObject(byte[] bytes) {

        Object obj = null;

        try (
                ByteArrayInputStream byteIn = new ByteArrayInputStream(bytes);
                ObjectInputStream objIn = new ObjectInputStream(byteIn)
        ) {
            obj = objIn.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return (T)obj;
    }

    public static int bytesToInteger(byte[] bytes){

        if (bytes.length < 4)
        {
            if (bytes.length != 0)
            {
                byte[] bytes1 = new byte[4];
                if (bytes.length == 3)
                {
                    bytes1[0] = 0x00;
                    bytes1[1] = bytes[0];
                    bytes1[2] = bytes[1];
                    bytes1[3] = bytes[2];
                }
                else if (bytes.length == 2)
                {
                    bytes1[0] = 0x00;
                    bytes1[1] = 0x00;
                    bytes1[2] = bytes[0];
                    bytes1[3] = bytes[1];
                }
                else if (bytes.length == 1)
                {
                    bytes1[0] = 0x00;
                    bytes1[1] = 0x00;
                    bytes1[2] = 0x00;
                    bytes1[3] = bytes[0];
                }
                return (bytes1[0] & 0xff) << 24
                        | (bytes1[1] & 0xff) << 16
                        | (bytes1[2] & 0xff) << 8
                        | (bytes1[3] & 0xff);
            }
            else
            {
                return 0;
            }
        }
        return (bytes[0] & 0xff) << 24
                | (bytes[1] & 0xff) << 16
                | (bytes[2] & 0xff) << 8
                | (bytes[3] & 0xff);

    }

    /**
     * int转byte[]
     */
    public static byte[] intToBytes(int i) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (i & 0xff);
        bytes[1] = (byte) ((i >> 8) & 0xff);
        bytes[2] = (byte) ((i >> 16) & 0xff);
        bytes[3] = (byte) ((i >> 24) & 0xff);
        return bytes;
    }

}
