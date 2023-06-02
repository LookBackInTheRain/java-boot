package club.yuit.basic.clazz.constantpool.parser;

import club.yuit.basic.clazz.annotations.ConstPoolLexer;
import club.yuit.basic.clazz.parser.Reader;

import java.util.List;

/**
 * @author yuit
 * @date 2022/5/23
 * 大端序 int
 **/
@ConstPoolLexer(3)
public class IntegerInfo extends AbstractConstantInfo {


    private int dataValue;

    public IntegerInfo(List<AbstractConstantInfo> pool) {
        super(pool);
    }

    @Override
    public void doParser(Reader reader) {
        this.dataValue = reader.readInt();
    }

    @Override
    public void print() {
        System.out.print("Integer\t\t"+ dataValue);
    }

    @Override
    public String getValue() {
        return String.valueOf(this.dataValue);
    }
}
