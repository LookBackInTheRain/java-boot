package club.yuit.basic.clazz.parser;

import club.yuit.basic.clazz.Struct;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author yuit
 * @date 2023/5/31
 **/
public interface Parser {



    int readByte();

    short readShort();
    int readUnsignedShort();
    int readInt();


    byte[] readBytes(int length);

}
