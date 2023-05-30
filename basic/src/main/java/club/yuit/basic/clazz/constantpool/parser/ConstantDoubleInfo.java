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
@ConstPoolParser(5)
public class ConstantDoubleInfo extends AbstractConstantInfo {

    private double dataValue;

    public ConstantDoubleInfo(List<ConstantInfo> pools) {
        super(pools);
    }


    @Override
    public void handle(InputStream in) throws IOException {
        byte[] buf = new byte[8];
        in.read(buf);
        this.dataValue = ByteUtil.bytesToDouble(buf, ByteOrder.BIG_ENDIAN);
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
