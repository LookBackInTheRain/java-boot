package club.yuit.basic.clazz.constantpool.parser;

import club.yuit.basic.clazz.parser.Reader;
import club.yuit.basic.clazz.annotations.ConstPoolLexer;
import lombok.Getter;

import java.util.List;

/**
 * @author yuit
 * @date 2022/5/23
 * 类或借口的符号引用
 **/
@Getter
@ConstPoolLexer(7)
public class ClassInfo extends AbstractConstantInfo {

    private int nameIndex;

    public ClassInfo(List<AbstractConstantInfo> pool) {
        super(pool);
    }

    @Override
    public void doParser(Reader reader)  {
        // u2
       this.nameIndex = reader.readU2();
    }

    @Override
    public void print() {
        System.out.print("Class\t\t\t\t#" + nameIndex);
        System.out.print("\t\t\t\t// "+getValue());
    }

    @Override
    public String getValue() {
        return pool.get(nameIndex-1).getValue();
    }
}
