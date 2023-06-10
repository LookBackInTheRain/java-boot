package club.yuit.basic.clazz.struct.attr;

import club.yuit.basic.clazz.utils.ByteBufferReader;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: yuit
 * @date: 2023/06/10 12:46
 */
@Getter
@Setter
public class LocalVariableTableInfo {

    /**
     * u2 局部变量的生命周期开始的字节码便偏移量
     */
    private int startPc;

    /**
     * u2 部变量作用范围覆盖的长度
     * 与startPc 结合表示局部变量在字节码中的作用范围
     *
     */
    private int length;

    /**
     * u2  常量池索引 CONSTANT_Utf8_info，表示局部变量名称
     */
    private int nameIndex;

    private String name;

    /**
     * u2 常量池索引 CONSTANT_Utf8_info, 局部变量描述符
     */
    private int descriptorIndex;


    private String descriptor;

    /**
     * u2 局部变量在栈帧的局部变量表中变量槽的位置
     */
    private int index;


    public  void  read(ByteBufferReader reader) {
        this.startPc = reader.readU2();
        this.length = reader.readU2();
        this.nameIndex = reader.readU2();
        this.descriptorIndex = reader.readU2();
        this.index = reader.readU2();
    }

}
