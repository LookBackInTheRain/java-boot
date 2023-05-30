package club.yuit.basic.clazz.constantpool.parser;

import club.yuit.basic.clazz.annotations.ConstPoolParser;
import cn.hutool.core.util.ByteUtil;
import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import java.util.List;

/**
 * @author yuit
 * @date 2022/5/23
 **/
@Getter
@ConstPoolParser(7)
public class ConstantClassInfo extends AbstractConstantInfo {

    private int nameIndex;

    public ConstantClassInfo(List<ConstantInfo> pools) {
        super(pools);
    }




    @Override
    public void handle(InputStream in) throws IOException {
        byte[] buf4 = new byte[4];
        in.read(buf4, 2, 2);
        this.nameIndex = ByteUtil.bytesToInt(buf4, ByteOrder.BIG_ENDIAN);
    }

    @Override
    public void print() {
        System.out.print("Class\t\t\t\t#" + nameIndex);
        System.out.print("\t\t\t\t// "+getValue());
    }



    @Override
    public String getValue() {
        return getPools().get(nameIndex-1).getValue();
    }
}
