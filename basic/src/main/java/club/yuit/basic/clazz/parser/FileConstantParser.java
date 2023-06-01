package club.yuit.basic.clazz.parser;

import club.yuit.basic.clazz.Struct;
import cn.hutool.core.util.HexUtil;

import java.io.InputStream;

/**
 * @author yuit
 * @date 2023/5/31
 * 处理class文件前六字节，魔数，小版本号，主版本号信息
 **/

public class FileConstantParser extends AbstractParser {


    public FileConstantParser(Struct struct) {
        super(struct);
    }


    @Override
    public void doParser(Struct struct) {
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
