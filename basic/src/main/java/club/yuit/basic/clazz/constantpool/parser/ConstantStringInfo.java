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
@ConstPoolLexer(8)
public class ConstantStringInfo extends AbstractConstantInfo {

    private String dataValue;

    public ConstantStringInfo(List<ConstantInfo> pools) {
        super(pools);
    }


    @Override
    public void doParser() {
        byte[] buf = new byte[2];
        in.read(buf);
        this.dataValue = ByteUtil.bytesToInt(buf,ByteOrder.BIG_ENDIAN)+"";
    }

    @Override
    public void print() {
        System.out.print("String\t\t#"+dataValue);
    }

    @Override
    public String getValue() {
        return dataValue;
    }
}
