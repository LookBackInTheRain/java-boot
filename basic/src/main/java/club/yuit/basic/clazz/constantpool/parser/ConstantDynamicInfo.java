package club.yuit.basic.clazz.constantpool.parser;

import club.yuit.basic.clazz.annotations.ConstPoolLexer;
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
@ConstPoolLexer(17)
public class ConstantDynamicInfo extends AbstractConstantInfo {

    private int bmaIndex;
    private int natIndex;

    public ConstantDynamicInfo(List<ConstantInfo> pools) {
        super(pools);
    }


    @Override
    public void handle(InputStream in) throws IOException {
        byte[] buf = new byte[2];
        // bootstrap_method_attr_index
        in.read(buf);
        this.bmaIndex = ByteUtil.bytesToInt(buf,ByteOrder.BIG_ENDIAN);
        clear(buf);
        // name_and_type_index
        in.read(buf);
        this.natIndex = ByteUtil.bytesToInt(buf,ByteOrder.BIG_ENDIAN);
    }

    @Override
    public void print() {
        System.out.print("Dynamic\t\t"+bmaIndex+".#"+natIndex);
        System.out.print("\t\t\t// "+getValue());
    }

    @Override
    public String getValue() {
        return getPools().get(bmaIndex-1)+"."+getPools().get(natIndex-1).getValue();
    }
}
