package club.yuit.basic.clazz;

import club.yuit.basic.clazz.struct.ClassAccessFlag;
import club.yuit.basic.clazz.constants.AccessFlagEnum;
import club.yuit.basic.clazz.struct.ClassAccessFlags;
import cn.hutool.core.util.HexUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuit
 * @date 2022/5/23
 **/
@Slf4j
public class AccessFlagsUtils {


    private final static List<AccessFlagEnum> FLAGS = new ArrayList<>();

    static {
        FLAGS.add(AccessFlagEnum.ACC_PUBLIC);
        FLAGS.add(AccessFlagEnum.ACC_FINAL);
        FLAGS.add(AccessFlagEnum.ACC_SUPER);
        FLAGS.add(AccessFlagEnum.ACC_INTERFACE);
        FLAGS.add(AccessFlagEnum.ACC_ABSTRACT);
        FLAGS.add(AccessFlagEnum.ACC_SYNTHETIC);
        FLAGS.add(AccessFlagEnum.ACC_ANNOTATION);
        FLAGS.add(AccessFlagEnum.ACC_ENUM);
        FLAGS.add(AccessFlagEnum.ACC_MODULE);
    }


    public static String convertToStrings(int value) {

        log.info("value:{},hex:{}",value, HexUtil.toHex(value));

        StringBuilder b = new StringBuilder();
        FLAGS.forEach(i->{
            if ((i.value&value)==i.value){
                b.append(i.name())
                        .append(",");
            }
        });

        if (b.length()>0){
            b.deleteCharAt(b.length()-1);
        }
        return b.toString();
    }


    public static ClassAccessFlags convertToFlags(int value){
        log.info("value:{},hex:{}",value, HexUtil.toHex(value));
        ClassAccessFlags flags = new ClassAccessFlags();
        flags.setAccessFlags(new ArrayList<>());
        FLAGS.forEach(i->{
            if ((i.value&value)==i.value){
                    ClassAccessFlag flag = new ClassAccessFlag();
                    flags.setValue(i.value);
                    flags.getAccessFlags().add(flag);
            }
        });
        return flags;
    }


    public static void main(String[] args) {
        System.out.println(convertToStrings(0x0021));
    }

}

