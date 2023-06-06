package club.yuit.basic.clazz.constantpool.parser;

import club.yuit.basic.clazz.annotations.ConstPoolLexer;
import club.yuit.basic.clazz.parser.Reader;

import java.util.List;

/**
 * @author yuit
 * @date 2022/5/23
 * 字符串类型字面量
 **/
@ConstPoolLexer(8)
public class StringInfo extends AbstractConstantInfo {

    /**
     * u2 字符串字面量的索引
     */
    private int index;

    public StringInfo(List<AbstractConstantInfo> pools) {
        super(pools);
    }


    @Override
    public void doParser(Reader reader) {
        this.index = reader.readU2();
    }

    @Override
    public void print() {
        System.out.print("String\t\t#"+getValue());
    }

    @Override
    public String getValue() {
        return pool.get(this.index).getValue();
    }
}
