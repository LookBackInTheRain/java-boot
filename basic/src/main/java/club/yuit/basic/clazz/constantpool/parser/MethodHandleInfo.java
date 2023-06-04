package club.yuit.basic.clazz.constantpool.parser;

import club.yuit.basic.clazz.annotations.ConstPoolLexer;
import club.yuit.basic.clazz.parser.Reader;

import java.util.List;

/**
 * @author yuit
 * @date 2022/5/23
 * 方法句柄
 **/
@ConstPoolLexer(15)
public class MethodHandleInfo extends AbstractConstantInfo {

    /**
     * u1 在[1-9]之间，决定了方法句柄的类型，方法句柄类型的值
     * 表示方法句柄的字节码行为
     */
    private int referenceKind;

    /**
     * u2 常量池有效索引
     */
    private int referenceIndex;

    public MethodHandleInfo(List<AbstractConstantInfo> pool) {
        super(pool);
    }


    @Override
    public void doParser(Reader reader) {
         referenceKind = reader.readByte();
         referenceIndex = reader.readUnsignedShort();
    }


    @Override
    public void print() {
        System.out.print("MethodHandle\t\t"+referenceKind+".#"+referenceIndex);
        System.out.print("\t\t\t// "+getValue());
    }

    @Override
    public String getValue() {
        return referenceKind+"."+pool.get(referenceIndex-1).getValue();
    }
}
