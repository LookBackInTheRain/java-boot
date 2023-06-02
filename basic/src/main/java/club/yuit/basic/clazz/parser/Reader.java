package club.yuit.basic.clazz.parser;

import club.yuit.basic.clazz.struct.ClassFile;
import cn.hutool.core.util.ByteUtil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;

/**
 * @author yuit
 * @date 2023/5/31
 **/
public interface Reader {


    default int readByte() {
        try {
            if (getStream().available() < 1) {
                throw new RuntimeException();
            }
            return this.getStream().read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    default short readShort() {
        try {
            if (getStream().available() < 2) {
                throw new RuntimeException();
            }
            byte[] b = this.getStream().readNBytes(2);
            return ByteUtil.bytesToShort(b, ByteOrder.BIG_ENDIAN);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    default int readUnsignedShort() {
        try {
            byte[] tmp = new byte[4];
            if (getStream().available() < 2) {
                throw new RuntimeException();
            }
            int read = 0;
            while (read != 2) {
                read += getStream().read(tmp, 2, 2);
            }
            return ByteUtil.bytesToInt(tmp, ByteOrder.BIG_ENDIAN);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    default int readInt() {
        try {
            if (getStream().available() < 4) {
                throw new RuntimeException();
            }
            byte[] tmp = this.getStream().readNBytes(4);
            return ByteUtil.bytesToInt(tmp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    default float readFloat() {
        int tmp = readInt();
        return Float.intBitsToFloat(tmp);
    }


    default byte[] readBytes(int length) {
        try {
            return this.getStream().readNBytes(length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    default long readLong(){

        try {
            if (getStream().available()<8){
                throw new RuntimeException();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    InputStream getStream();


}
