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
@ConstPoolLexer(4)
public class ConstantFloatInfo extends AbstractConstantInfo {

    private float dataValue;

    public ConstantFloatInfo(List<ConstantInfo> pools) {
        super(pools);
    }


    @Override
    public void handle(InputStream in) throws IOException {
        byte[] buf4 = new byte[4];
        in.read(buf4);
         int v =   ByteUtil.bytesToInt( buf4, ByteOrder.BIG_ENDIAN);
         dataValue = Float.intBitsToFloat(v);

    }

    @Override
    public void print() {
        System.out.print("Float\t\t"+ dataValue);
    }

    @Override
    public String getValue() {
        return dataValue+"";
    }
}
