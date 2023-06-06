package club.yuit.basic.clazz.constantpool.parser;

import club.yuit.basic.clazz.annotations.ConstPoolLexer;
import club.yuit.basic.clazz.parser.Reader;

import java.util.List;

/**
 * @author yuit
 * @date 2022/5/23
 * 方法或字段的部分符号引用
 **/
@ConstPoolLexer(12)
public class NameAndTypeInfo extends AbstractConstantInfo {

    /**
     * u2 方法或字段名称常量池索引
     */
    private int nameIndex;
    /**
     * u2 字段或方法描述的常量池索引
     */
    private int descIndex;

    public NameAndTypeInfo(List<AbstractConstantInfo> pool) {
        super(pool);
    }

    @Override
    public void doParser(Reader reader){
         nameIndex = reader.readU2();
         descIndex = reader.readU2();
    }


    @Override
    public void print() {
        System.out.print("NameAndType\t\t\t#"+nameIndex+":#"+descIndex);
        System.out.print("\t\t\t// "+getValue());
    }

    @Override
    public String getValue() {
        return pool.get(nameIndex-1).getValue()+":"+pool.get(descIndex-1).getValue();
    }
}
