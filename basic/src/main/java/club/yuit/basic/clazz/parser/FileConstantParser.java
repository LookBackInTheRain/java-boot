package club.yuit.basic.clazz.parser;

import club.yuit.basic.clazz.struct.Struct;
import cn.hutool.core.util.HexUtil;

import java.io.FileReader;

/**
 * @author yuit
 * @date 2023/5/31
 * 处理class文件前六字节，魔数，小版本号，主版本号信息
 **/

public class FileConstantParser extends AbstractParser {



    @Override
    public void doParser(Reader reader,Struct struct) {
        // 魔数
        int magic = reader.readInt();
        // minor_version
        int minorVersion = reader.readUnsignedShort();
        int majorVersion = reader.readUnsignedShort();
        int cpCount = reader.readUnsignedShort();
        struct.setMagic(HexUtil.toHex(magic));
        struct.setMinorVersion(minorVersion);
        struct.setMajorVersion(majorVersion);
        struct.setConstantPoolCount(cpCount);
    }


}
