package club.yuit.basic.clazz.parser;

import club.yuit.basic.clazz.Struct;
import club.yuit.basic.clazz.annotations.Lexer;
import cn.hutool.core.util.HexUtil;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author yuit
 * @date 2023/5/31
 * 处理class文件前六字节，魔数，小版本号，主版本号信息
 **/
@Lexer(length = 8)
public class FileConstantParser extends AbstractParser {


    public FileConstantParser(FileChannel channel) {
        super(channel);
    }


    @Override
    public void handle(Struct struct) {
        // 魔数
        int magic = readInt();
        // minor_version
        int minorVersion = readUnsignedShort();
        int majorVersion = readUnsignedShort();
        int cpCount = readUnsignedShort();
        struct.setMagic(HexUtil.toHex(magic));
        struct.setMinorVersion(minorVersion);
        struct.setMajorVersion(majorVersion);
        struct.setConstantPoolCount(cpCount);
    }


}
