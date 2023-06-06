package club.yuit.basic.clazz.constantpool.parser;

import club.yuit.basic.clazz.annotations.ConstPoolLexer;
import club.yuit.basic.clazz.parser.Reader;

import java.util.List;

/**
 * @author yuit
 * @date 2022/5/23
 * 方法类型
 **/
@ConstPoolLexer(16)
public class MethodTypeInfo extends AbstractConstantInfo {

    /**
     * u2 值为常量池有效索引且索引处的项必须为 CONSTANT_Utf8_Info
     * 表示方法的描述符
     */
    private int descIndex;

    public MethodTypeInfo(List<AbstractConstantInfo> pools) {
        super(pools);
    }


    @Override
    public void doParser(Reader reader) {

        descIndex =  reader.readU2();
    }

    @Override
    public void print() {
        System.out.println("Class\t\t#" + descIndex);
        System.out.println("\t\t\t// " + getValue());
    }

    @Override
    public String getValue() {
        return pool.get(descIndex - 1).getValue();
    }
}
