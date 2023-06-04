package club.yuit.basic.clazz.constantpool.parser;

import club.yuit.basic.clazz.annotations.ConstPoolLexer;
import club.yuit.basic.clazz.parser.Reader;

import java.util.List;

/**
 * @author yuit
 * @date 2022/5/23
 * 表示一个模块
 **/
@ConstPoolLexer(19)
public class ModuleInfo extends AbstractConstantInfo {

    /**
     * u2 常量池有效索引且所以项值必须为CONSTANT_Utf8_Info
     * 表示模块名称
     */
    private int nameIndex;

    public ModuleInfo(List<AbstractConstantInfo> pools) {
        super(pools);
    }


    @Override
    public void doParser(Reader reader) {
         nameIndex = reader.readUnsignedShort();
    }

    @Override
    public void print() {
        System.out.println("Module\t\t#" + nameIndex);
        System.out.println("\t\t\t// " + getValue());
    }

    @Override
    public String getValue() {
        return pool.get(nameIndex-1).getValue();
    }
}
