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
@ConstPoolParser(3)
public class ConstantIntegerInfo extends AbstractConstantInfo {


    private int dataValue;

    public ConstantIntegerInfo(List<ConstantInfo> pools) {
        super(pools);
    }

    @Override
    public void handle(InputStream in) throws IOException {
        byte[] buf4 = new byte[4];
        in.read(buf4);
        this.dataValue = ByteUtil.bytesToInt(buf4, ByteOrder.BIG_ENDIAN);
    }

    @Override
    public void print() {
        System.out.print("Integer\t\t"+ dataValue);
    }

    @Override
    public String getValue() {
        return dataValue+"";
    }
}
