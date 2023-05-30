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
@ConstPoolParser(6)
public class ConstantLongInfo extends AbstractConstantInfo {

    private long dataValue;

    public ConstantLongInfo(List<ConstantInfo> pools) {
        super(pools);
    }


    @Override
    public void handle(InputStream in) throws IOException {
        byte[] buf = new byte[8];
        in.read(buf);
        dataValue = ByteUtil.bytesToLong(buf, ByteOrder.BIG_ENDIAN);
    }

    @Override
    public void print() {
        System.out.print("Double\t\t"+ this.dataValue);
    }


    @Override
    public String getValue() {
        return this.dataValue+"";
    }
}
