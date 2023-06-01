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
@ConstPoolLexer(19)
public class ConstantModuleInfo  extends AbstractConstantInfo {

    private int nameIndex;

    public ConstantModuleInfo(List<ConstantInfo> pools) {
        super(pools);
    }


    @Override
    public void handle(InputStream in) throws IOException {
        byte[] buf4 = new byte[4];
        in.read(buf4, 2, 2);
         nameIndex = ByteUtil.bytesToInt(buf4, ByteOrder.BIG_ENDIAN);
    }

    @Override
    public void print() {
        System.out.println("Module\t\t#" + nameIndex);
        System.out.println("\t\t\t// " + getValue());
    }

    @Override
    public String getValue() {
        return getPools().get(nameIndex-1).getValue();
    }
}
