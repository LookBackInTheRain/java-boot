package club.yuit.basic.clazz.struct.attr;

import club.yuit.basic.clazz.struct.AttributeInfo;
import club.yuit.basic.clazz.struct.AttributeItem;
import cn.hutool.core.util.ByteUtil;
import cn.hutool.core.util.HexUtil;
import lombok.Getter;
import lombok.Setter;

import java.nio.ByteOrder;
import java.util.List;

/**
 * @author yuit
 * @date 2023/6/6
 **/
@Getter
@Setter
public class Code extends AttributeItem {

    /**
     * u2 操作数栈最大深度
     */
    private int maxStack;

    /**
     * u2 局部变量表的所需的存储空间
     */
    private int maxLocals;

    /**
     * u4 (实际只用两个字节) 字节码长度
     */
    private int codeLength;

    /**
     * 字节码指令
     */
    private byte[] codes;

    private ByteCodeDesc[] descs;


    /**
     * 异常表容量
     */
    private int expTableLength;

    /**
     * 异常表
     */
    private List<ExceptionInfo> exceptionTable;

    /**
     * 属性
     */
    private AttributeInfo attributeInfo;


    public void setCodes(byte[] codes) {
        if (codes.length > 0) {
            this.descs = new ByteCodeDesc[codeLength];
            for (int i = 0; i < codes.length; i++) {
                ByteCodeDesc desc = new ByteCodeDesc();
                desc.setValue(codes[i]&0xff);
                desc.setHexValue(HexUtil.toHex(desc.getValue()));
                desc.setDesc(ByteCode.value(desc.getValue()));
                   descs[i] = desc;
            }
        }
        this.codes = codes;

    }
}

@Getter
@Setter
class ByteCodeDesc {
    private int value;
    private String hexValue;

    private String desc;
}


