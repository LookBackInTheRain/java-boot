package club.yuit.basic.clazz.struct.attr;

import club.yuit.basic.clazz.constantpool.parser.AbstractConstantInfo;
import club.yuit.basic.clazz.struct.Struct;
import club.yuit.basic.clazz.utils.ByteBufferReader;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: yuit
 * @date: 2023/06/10 12:46
 */
@Getter
@Setter
public class LocalVariableTypeTableInfo {

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

    /**
     * nameIndex CONSTANT_Utf8_info 表示的值
     */
    private String name;

    /**
     * u2 常量池索引 CONSTANT_Utf8_info
     */
    private int signatureIndex;

    /**
     * signatureIndex CONSTANT_Utf8_info 中的值
     */
    private String signature;

    /**
     * u2 局部变量在栈帧的局部变量表中变量槽的位置
     */
    private int index;




}
