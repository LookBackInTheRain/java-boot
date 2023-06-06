package club.yuit.basic.clazz.constants;

import cn.hutool.core.util.HexUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

/**
 * @author yuit
 * @date 2023/6/6
 **/
@Slf4j
public enum MethodAccessFlagEnum  {
    ACC_PUBLIC(0x0001,"public"),
    ACC_PRIVATE(0x00002,"private"),
    ACC_PROTECTED(0x0004,"protected"),
    ACC_STATIC(0x0008,"static"),
    ACC_FINAL(0x0010,"final"),
    ACC_VOLATILE(0x0040,"volatile"),
    ACC_TRANSIENT(0x1000,"transient"),

    /**
     * 是否由编译器自动产生
     */
    ACC_SYNIHETIC(0x1000,"synihetic"),
    /**
     * 是否为枚举
     */
    ACC_ENUM(0x4000,"enum");


    public final int value;
    public final String desc;

    MethodAccessFlagEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }


    public static List<MethodAccessFlagEnum> get(int val){
        MethodAccessFlagEnum[] values = MethodAccessFlagEnum.values();
        List<MethodAccessFlagEnum> flags = Arrays.asList(values);
        flags.removeIf(i->(i.value&val)!=i.value);

        return flags;
    }


    public static String convertToStrings(int value) {
        log.info("value:{},hex:{}",value, HexUtil.toHex(value));
        StringBuilder b = new StringBuilder();
        for (MethodAccessFlagEnum i : MethodAccessFlagEnum.values()) {
            if ((i.value&value)==i.value){
                b.append(i.desc)
                        .append(" ");
            }
        }
        if (b.length()>0){
            b.deleteCharAt(b.length()-1);
        }
        return b.toString();
    }

}
