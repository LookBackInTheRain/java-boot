package club.yuit.basic.clazz.constantpool.parser;

import club.yuit.basic.clazz.parser.Reader;
import club.yuit.basic.clazz.annotations.ConstPoolLexer;

import java.util.List;

/**
 * @author yuit
 * @date 2022/5/23
 * 浮点型字面量
 **/
@ConstPoolLexer(4)
public class FloatInfo extends AbstractConstantInfo {

    private float dataValue;

    public FloatInfo(List<AbstractConstantInfo> pool) {
        super(pool);
    }


    @Override
    public void doParser(Reader reader)  {
        // u4
         int v =   reader.readInt();
         dataValue = Float.intBitsToFloat(v);
    }

    @Override
    public void print() {
        System.out.print("Float\t\t"+ dataValue);
    }

    @Override
    public String getValue() {
        return String.valueOf(this.dataValue);
    }
}
