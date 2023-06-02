package club.yuit.basic.clazz.constantpool.parser;

import club.yuit.basic.clazz.annotations.ConstPoolLexer;
import club.yuit.basic.clazz.parser.Reader;
import cn.hutool.core.util.ByteUtil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import java.util.List;

/**
 * @author yuit
 * @date 2022/5/23
 **/
@ConstPoolLexer(6)
public class ConstantLongInfo extends AbstractConstantInfo {

    private long dataValue;

    public ConstantLongInfo(List<AbstractConstantInfo> pool) {
        super(pool);
    }


    @Override
    public void doParser(Reader reader) {
        dataValue = reader.read
    }

    @Override
    public void print() {
        System.out.print("Double\t\t"+ this.dataValue);
    }


    @Override
    public String getValue() {
        return String.valueOf(this.dataValue);
    }
}
