package club.yuit.basic.clazz.constantpool.parser;

import club.yuit.basic.clazz.annotations.ConstPoolLexer;
import club.yuit.basic.clazz.parser.Reader;

import java.util.List;

/**
 * @author yuit
 * @date 2022/5/23
 * 动态方法调用点
 **/
@ConstPoolLexer(18)
public class InvokeDynamicInfo extends AbstractConstantInfo {


    /**
     * u2 值必须是对当前Class 文件中引导方发表bootstrap_methods[]数组的有效索引
     */
    private int bmaIndex;

    /**
     * u2 当前常量池的有效索引，索引标识的必须是CONSTANT_NameAndType_Info 结构；表示方法名和方法描述符
     */
    private int natIndex;

    public InvokeDynamicInfo(List<AbstractConstantInfo> pool) {
        super(pool);
    }


    @Override
    public void doParser(Reader reader) {
        // bootstrap_method_attr_index
        bmaIndex = reader.readUnsignedShort();
        // name_and_type_index
        natIndex = reader.readUnsignedShort();
    }

    @Override
    public void print() {
        System.out.print("InvokeDynamic\t\t"+bmaIndex+".#"+natIndex);
        System.out.print("\t\t\t// "+getValue());
    }

    @Override
    public String getValue() {
        return pool.get(bmaIndex-1)+"."+pool.get(natIndex-1).getValue();
    }
}
