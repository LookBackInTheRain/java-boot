package club.yuit.basic.clazz.constantpool.parser;

import club.yuit.basic.clazz.annotations.ConstPoolLexer;
import club.yuit.basic.clazz.parser.Reader;

import java.util.List;

/**
 * @author yuit
 * @date 2022/5/23
 * 一个模块中开放或导出的包
 **/
@ConstPoolLexer(20)
public class PackageInfo extends AbstractConstantInfo {


    /**
     * u2 常量池有效索引且索引处的项为CONSTANT_Utf8_Info
     * 表示包名称
     */
    private int nameIndex;


    @Override
    public void doParser(Reader reader){
         nameIndex = reader.readU2();
    }

    public PackageInfo(List<AbstractConstantInfo> pools) {
        super(pools);
    }

    @Override
    public void print() {
        System.out.println("Package\t\t#" + nameIndex);
        System.out.print("\t\t\t// "+getValue());
    }

    @Override
    public String getValue() {
        return pool.get(nameIndex-1).getValue();
    }
}
