package club.yuit.basic.clazz.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuit
 * @date 2022/5/23
 **/
public class AccessFlags {


    private final static List<Flags> FLAGS = new ArrayList<>();

    static {
        FLAGS.add(Flags.ACC_PUBLIC);
        FLAGS.add(Flags.ACC_FINAL);
        FLAGS.add(Flags.ACC_SUPER);
        FLAGS.add(Flags.ACC_INTERFACE);
        FLAGS.add(Flags.ACC_ABSTRACT);
        FLAGS.add(Flags.ACC_SYNTHETIC);
        FLAGS.add(Flags.ACC_ANNOTATION);
        FLAGS.add(Flags.ACC_ENUM);
        FLAGS.add(Flags.ACC_MODULE);
    }


    public static String convertToStrings(int value) {
        StringBuilder b = new StringBuilder();

        return null;
    }


    public static void main(String[] args) {
        System.out.println(Flags.ACC_PUBLIC.name());
    }

}

enum Flags {

    ACC_PUBLIC(0x0001),
    ACC_FINAL(0x0010),
    ACC_SUPER(0x0020),
    ACC_INTERFACE(0x0200),
    ACC_ABSTRACT(0x0400),
    ACC_SYNTHETIC(0x1000),
    ACC_ANNOTATION(0x2000),
    ACC_ENUM(0x4000),
    ACC_MODULE(0x8000);


    int value;

    Flags(int value) {
        this.value = value;
    }


}
