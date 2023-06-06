package club.yuit.basic.clazz.constantpool.parser;

import club.yuit.basic.clazz.annotations.ConstPoolLexer;
import club.yuit.basic.clazz.parser.Reader;

import java.util.List;

/**
 * @author yuit
 * @date 2022/5/23
 * 接口方法符号引用
 **/
@ConstPoolLexer(11)
public class InterfaceMethodRefInfo extends AbstractConstantInfo {

    /**
     * u2 指向声明方法的接口描述符 CONSTANT_Class_Info 的索引
     */
    private int classIndex;

    /**
     * u2 指向名称及类型描述符 CONSTANT_NameAndType_Info 的索引
     */
    private int nameAndTypeIndex;

    public InterfaceMethodRefInfo(List<AbstractConstantInfo> pool) {
        super(pool);
    }


    @Override
    public void doParser(Reader reader) {
         classIndex = reader.readU2();
         nameAndTypeIndex = reader.readU2();
    }

    @Override
    public void print() {
        System.out.print("InterfaceMethodref\t\t#"+classIndex+".#"+nameAndTypeIndex);
        System.out.print("\t\t\t// "+getValue());
    }

    @Override
    public String getValue() {
        return pool.get(classIndex-1)+"."+pool.get(nameAndTypeIndex-1);
    }
}
