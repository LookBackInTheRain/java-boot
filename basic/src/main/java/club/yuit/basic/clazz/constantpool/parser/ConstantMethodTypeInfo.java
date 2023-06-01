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
@ConstPoolLexer(16)
public class ConstantMethodTypeInfo extends AbstractConstantInfo {

    private int descIndex;

    public ConstantMethodTypeInfo(List<ConstantInfo> pools) {
        super(pools);
    }


    @Override
    public void handle(InputStream in) throws IOException {
        byte[] buf = new byte[2];
        in.read(buf);
        descIndex = ByteUtil.bytesToInt(buf, ByteOrder.BIG_ENDIAN);
    }

    @Override
    public void print() {
        System.out.println("Class\t\t#" + descIndex);
        System.out.println("\t\t\t// " + getValue());
    }

    @Override
    public String getValue() {
        return getPools().get(descIndex - 1).getValue();
    }
}
