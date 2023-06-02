package club.yuit.basic.clazz.constantpool.parser;

import club.yuit.basic.clazz.annotations.ConstPoolLexer;
import cn.hutool.core.util.ByteUtil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author yuit
 * @date 2022/5/23
 **/
@ConstPoolLexer(1)
public class ConstantUtf8Info extends AbstractConstantInfo {


    private String data;

    public ConstantUtf8Info(List<ConstantInfo> pools) {
        super(pools);
    }



    @Override
    public void doParser() {
        byte[] buf4 = new byte[4];
        in.read(buf4,2,2);
        int length = ByteUtil.bytesToInt(buf4, ByteOrder.BIG_ENDIAN);
        byte[] buf = new byte[length];
        in.read(buf);
        if (length<=0x7f){
            data = new String(buf, StandardCharsets.US_ASCII);
        }else if (length<0x7ff){
            data = new String(buf,"GBK");
        }else {
            data = new String(buf, StandardCharsets.UTF_8);
        }
    }

    @Override
    public void print() {
        System.out.print("Utf8\t\t\t\t"+this.data);
    }

    @Override
    public String getValue() {
        return this.data;
    }
}
