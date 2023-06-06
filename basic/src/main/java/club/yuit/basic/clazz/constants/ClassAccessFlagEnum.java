package club.yuit.basic.clazz.constants;

import club.yuit.basic.clazz.struct.ClassAccessFlag;
import club.yuit.basic.clazz.struct.ClassAccessFlags;
import cn.hutool.core.util.HexUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * @author: yuit
 * @date: 2023/05/30 20:17
 */
@Slf4j
public enum ClassAccessFlagEnum {

    /**
     * public
     */
    ACC_PUBLIC(0x0001),

    /**
     * final
     */
    ACC_FINAL(0x0010),

    /**
     * 是否允许使用invokespecial 字节码指令的新语义
     */
    ACC_SUPER(0x0020),
    /**
     * 接口
     */
    ACC_INTERFACE(0x0200),
    /**
     * 抽象类
     */
    ACC_ABSTRACT(0x0400),
    /**
     *  标记不是用户代码产生
     */
    ACC_SYNTHETIC(0x1000),
    /**
     * 注解
     */
    ACC_ANNOTATION(0x2000),
    /**
     * 枚举
     */
    ACC_ENUM(0x4000),
    /**
     * 模块
     */
    ACC_MODULE(0x8000);


    public final int value;

    ClassAccessFlagEnum(int value) {
        this.value = value;
    }


    public static String convertToStrings(int value) {

        log.info("value:{},hex:{}",value, HexUtil.toHex(value));

        StringBuilder b = new StringBuilder();

        for (ClassAccessFlagEnum i : ClassAccessFlagEnum.values()) {
            if ((i.value&value)==i.value){
                b.append(i.name())
                        .append(",");
            }
        }
        if (b.length()>0){
            b.deleteCharAt(b.length()-1);
        }
        return b.toString();
    }


    public static ClassAccessFlags convertToFlags(int value){
        log.info("value:{},hex:{}",value, HexUtil.toHex(value));
        ClassAccessFlags flags = new ClassAccessFlags();
        flags.setAccessFlags(new ArrayList<>());
        for (ClassAccessFlagEnum i : ClassAccessFlagEnum.values()) {
            if ((i.value&value)==i.value){
                ClassAccessFlag flag = new ClassAccessFlag();
                flags.setValue(i.value);
                flags.getAccessFlags().add(flag);
            }
        }
        return flags;
    }

}
