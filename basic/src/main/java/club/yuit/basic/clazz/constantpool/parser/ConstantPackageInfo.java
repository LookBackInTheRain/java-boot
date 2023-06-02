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
@ConstPoolLexer(20)
public class ConstantPackageInfo extends AbstractConstantInfo {

    private int nameIndex;


    @Override
    public void doParser(){
        byte[] buf4 = new byte[4];
        in.read(buf4, 2, 2);
         nameIndex = ByteUtil.bytesToInt(buf4, ByteOrder.BIG_ENDIAN);


    }

    public ConstantPackageInfo(List<ConstantInfo> pools) {
        super(pools);
    }

    @Override
    public void print() {
        System.out.println("Package\t\t#" + nameIndex);
        System.out.print("\t\t\t// "+getValue());
    }

    @Override
    public String getValue() {
        return getPools().get(nameIndex-1).getValue();
    }
}
