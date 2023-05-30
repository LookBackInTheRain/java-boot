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
@ConstPoolParser(18)
public class ConstantInvokeDynamicInfo extends AbstractConstantInfo {

    private int bmaIndex;
    private int natIndex;

    public ConstantInvokeDynamicInfo(List<ConstantInfo> pools) {
        super(pools);
    }


    @Override
    public void handle(InputStream in) throws IOException {
        byte[] buf = new byte[4];
        // bootstrap_method_attr_index
        in.read(buf,2,2);
        bmaIndex = ByteUtil.bytesToInt(buf,ByteOrder.BIG_ENDIAN);
        clear(buf);
        // name_and_type_index
        in.read(buf);
        natIndex = ByteUtil.bytesToInt(buf,ByteOrder.BIG_ENDIAN);

    }

    @Override
    public void print() {
        System.out.print("InvokeDynamic\t\t"+bmaIndex+".#"+natIndex);
        System.out.print("\t\t\t// "+getValue());
    }

    @Override
    public String getValue() {
        return getPools().get(bmaIndex-1)+"."+getPools().get(natIndex-1).getValue();
    }
}
