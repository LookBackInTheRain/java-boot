package club.yuit.basic.clazz.constantpool.parser;

import club.yuit.basic.clazz.annotations.ConstPoolParser;
import cn.hutool.core.util.ByteUtil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import java.util.List;

/**
 * @author yuit
 * @date 2022/5/23
 **/
@ConstPoolParser(10)
public class ConstantMethodrefInfo extends AbstractConstantInfo {

    private int classIndex;
    private int nameAndTypeIndex;

    public ConstantMethodrefInfo(List<ConstantInfo> pools) {
        super(pools);
    }


    @Override
    public void handle(InputStream in) throws IOException {
        byte[] buf = new byte[4];
        in.read(buf,2,2);
         classIndex = ByteUtil.bytesToInt(buf,ByteOrder.BIG_ENDIAN);
        clear(buf);
        in.read(buf,2,2);
         nameAndTypeIndex = ByteUtil.bytesToInt(buf,ByteOrder.BIG_ENDIAN);

    }

    @Override
    public void print() {
        System.out.print("Methodref\t\t\t#"+classIndex+".#"+nameAndTypeIndex);
        System.out.print("\t\t\t// "+getValue());
    }

    @Override
    public String getValue() {
        return getPools().get(classIndex-1).getValue()+"."+getPools().get(nameAndTypeIndex-1).getValue();
    }
}
