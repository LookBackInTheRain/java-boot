package club.yuit.basic.clazz.constantpool.parser;

import club.yuit.basic.clazz.Struct;
import club.yuit.basic.clazz.annotations.ConstPoolLexer;
import club.yuit.basic.clazz.parser.AbstractParser;
import cn.hutool.core.util.ByteUtil;
import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import java.util.List;

/**
 * @author yuit
 * @date 2022/5/23
 **/
@Getter
@ConstPoolLexer(7)
public class ConstantClassInfo extends AbstractParser {

    private int nameIndex;

    private Struct struct;

    public ConstantClassInfo(Struct struct) {
        super(struct);

    }

    @Override
    public void doParser() throws IOException {
       this.nameIndex = readUnsignedShort();
    }

    @Override
    public void print() {
        System.out.print("Class\t\t\t\t#" + nameIndex);
        System.out.print("\t\t\t\t// "+getValue());
    }



    @Override
    public String getValue() {
        return getPools().get(nameIndex-1).getValue();
    }
}
