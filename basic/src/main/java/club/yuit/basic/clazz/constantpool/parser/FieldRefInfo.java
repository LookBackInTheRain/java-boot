package club.yuit.basic.clazz.constantpool.parser;

import club.yuit.basic.clazz.parser.Reader;
import club.yuit.basic.clazz.annotations.ConstPoolLexer;

import java.util.List;

/**
 * @author yuit
 * @date 2022/5/23
 * 字段符号引用
 **/
@ConstPoolLexer(9)
public class FieldRefInfo extends AbstractConstantInfo {

    private int classIndex;
    private int nameAndTypeIndex;

    public FieldRefInfo(List<AbstractConstantInfo> pool) {
        super(pool);
    }


    @Override
    public void doParser(Reader reader) {
        // u2
        classIndex = reader.readU2();
        // u2
        nameAndTypeIndex = reader.readU2();
    }

    @Override
    public void print() {
        System.out.print("Fieldref\t\t\t#" + classIndex + ".#" + nameAndTypeIndex);
        System.out.print("\t\t\t// " + getValue());
    }

    @Override
    public String getValue() {
        return pool.get(classIndex - 1).getValue() + "." + pool.get(nameAndTypeIndex - 1).getValue();
    }
}
