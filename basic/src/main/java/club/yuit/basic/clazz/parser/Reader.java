package club.yuit.basic.clazz.parser;

import club.yuit.basic.clazz.struct.ClassFile;
import cn.hutool.core.util.ByteUtil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * @author yuit
 * @date 2023/5/31
 **/
public interface Reader {


    default int readU1() {
        try {
            if (getStream().available() < 1) {
                throw new RuntimeException();
            }
            byte[] tmp = new byte[2];
            getStream().read(tmp, 1, 1);
            return ByteUtil.bytesToShort(tmp, ByteOrder.BIG_ENDIAN);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    default int readU2() {
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


    default long readU4() {
        try {
            byte[] tmp = new byte[8];
            if (getStream().available() < 2) {
                throw new RuntimeException();
            }
            int read = 0;
            while (read != 4) {
                read += getStream().read(tmp, 4, 4);
            }
            return ByteUtil.bytesToLong(tmp, ByteOrder.BIG_ENDIAN);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    default int readInt() {

        byte[] tmp = readBytes(4);
        return ByteUtil.bytesToInt(tmp, ByteOrder.BIG_ENDIAN);

    }


    default byte[] readBytes(int length) {
        try {
            return this.getStream().readNBytes(length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    default long readLong() {

        try {
            if (getStream().available() < 8) {
                throw new RuntimeException();
            }
            byte[] bytes = getStream().readNBytes(8);
            return ByteUtil.bytesToLong(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    default ByteBuffer readBuffer(int length) {
        try {
            if (getStream().available() < length) {
                throw new RuntimeException();
            }
            byte[] bytes = getStream().readNBytes(length);
            return ByteBuffer.wrap(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    InputStream getStream();


}
