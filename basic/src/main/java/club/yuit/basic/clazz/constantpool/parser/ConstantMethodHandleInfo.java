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
@ConstPoolParser(15)
public class ConstantMethodHandleInfo extends AbstractConstantInfo {

    private int referenceKind;
    private int referenceIndex;

    public ConstantMethodHandleInfo(List<ConstantInfo> pools) {
        super(pools);
    }


    @Override
    public void handle(InputStream in) throws IOException {
        byte[] buf = new byte[2];
        in.read(buf,1,1);
         referenceKind = ByteUtil.bytesToInt(buf,ByteOrder.BIG_ENDIAN);
        clear(buf);
        in.read(buf);
         referenceIndex = ByteUtil.bytesToInt(buf,ByteOrder.BIG_ENDIAN);

    }


    @Override
    public void print() {
        System.out.print("MethodHandle\t\t"+referenceKind+".#"+referenceIndex);
        System.out.print("\t\t\t// "+getValue());
    }

    @Override
    public String getValue() {
        return referenceKind+"."+getPools().get(referenceIndex-1).getValue();
    }
}
