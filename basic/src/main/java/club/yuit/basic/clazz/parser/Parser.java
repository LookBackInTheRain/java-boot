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

    ByteBuffer read(FileChannel in);

    void handle(Struct struct);

}
