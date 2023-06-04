package club.yuit.basic.clazz.constantpool.parser;

import club.yuit.basic.clazz.annotations.ConstPoolLexer;
import club.yuit.basic.clazz.parser.Reader;

import java.util.List;

/**
 * @author yuit
 * @date 2022/5/23
 **/
@ConstPoolLexer(6)
public class LongInfo extends AbstractConstantInfo {

    private long dataValue;

    public LongInfo(List<AbstractConstantInfo> pool) {
        super(pool);
    }


    @Override
    public void doParser(Reader reader) {
        dataValue = reader.readLong();
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
