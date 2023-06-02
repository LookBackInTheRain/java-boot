package club.yuit.basic.clazz.constantpool.parser;

import club.yuit.basic.clazz.parser.Reader;
import club.yuit.basic.clazz.annotations.ConstPoolLexer;
import cn.hutool.core.util.ByteUtil;

import java.nio.ByteOrder;
import java.util.List;

/**
 * @author yuit
 * @date 2022/5/23
 * 双精度浮点型字面量
 **/
@ConstPoolLexer(5)
public class DoubleInfo extends AbstractConstantInfo {

    private double dataValue;

    public DoubleInfo(List<AbstractConstantInfo> pool) {
        super(pool);
    }


    @Override
    public void doParser(Reader reader)  {
        // u8
        byte[] buf = reader.readBytes(8);
        this.dataValue = ByteUtil.bytesToDouble(buf, ByteOrder.BIG_ENDIAN);
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
