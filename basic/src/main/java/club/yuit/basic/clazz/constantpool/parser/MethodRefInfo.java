package club.yuit.basic.clazz.constantpool.parser;

import club.yuit.basic.clazz.annotations.ConstPoolLexer;
import club.yuit.basic.clazz.parser.Reader;

import java.util.List;

/**
 * @author yuit
 * @date 2022/5/23
 * 类方法的符号引用
 **/
@ConstPoolLexer(10)
public class MethodRefInfo extends AbstractConstantInfo {

    /**
     * u2 指向声明方法的类描述符 CONSTANT_Class_Info 的索引
     */
    private int classIndex;

    /**
     * u2 指向名称及类型描述符CONSTANT_NameAndType_Info 的索引
     */
    private int nameAndTypeIndex;

    public MethodRefInfo(List<AbstractConstantInfo> pools) {
        super(pools);
    }


    @Override
    public void doParser(Reader reader) {
         classIndex =  reader.readUnsignedShort();
         nameAndTypeIndex = reader.readUnsignedShort();

    }

    @Override
    public void print() {
        System.out.print("Methodref\t\t\t#"+classIndex+".#"+nameAndTypeIndex);
        System.out.print("\t\t\t// "+getValue());
    }

    @Override
    public String getValue() {
        return pool.get(classIndex-1).getValue()+"."+pool.get(nameAndTypeIndex-1).getValue();
    }
}
