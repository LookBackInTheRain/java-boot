package club.yuit.basic.clazz.constantpool.parser;

import club.yuit.basic.clazz.annotations.ConstPoolLexer;
import cn.hutool.core.util.ByteUtil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import java.util.List;

/**
 * @author yuit
 * @date 2022/5/23
 **/
@ConstPoolLexer(12)
public class ConstantNameAndTypeInfo extends AbstractConstantInfo {

    private int nameIndex;
    private int descIndex;

    @Override
    public void handle(InputStream in) throws IOException {
        byte[] buf = new byte[4];
        in.read(buf,2,2);
         nameIndex = ByteUtil.bytesToInt(buf,ByteOrder.BIG_ENDIAN);
        clear(buf);
        in.read(buf,2,2);
         descIndex = ByteUtil.bytesToInt(buf,ByteOrder.BIG_ENDIAN);

    }

    public ConstantNameAndTypeInfo(List<ConstantInfo> pools) {
        super(pools);
    }

    @Override
    public void print() {
        System.out.print("NameAndType\t\t\t#"+nameIndex+":#"+descIndex);
        System.out.print("\t\t\t// "+getValue());
    }

    @Override
    public String getValue() {
        return getPools().get(nameIndex-1).getValue()+":"+getPools().get(descIndex-1).getValue();
    }
}
