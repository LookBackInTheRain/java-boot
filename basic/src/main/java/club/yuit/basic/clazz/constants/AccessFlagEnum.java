package club.yuit.basic.clazz.constants;

/**
 * @author: yuit
 * @date: 2023/05/30 20:17
 */
public enum AccessFlagEnum {
    ACC_PUBLIC(0x0001),
    ACC_FINAL(0x0010),
    ACC_SUPER(0x0020),
    ACC_INTERFACE(0x0200),
    ACC_ABSTRACT(0x0400),
    ACC_SYNTHETIC(0x1000),
    ACC_ANNOTATION(0x2000),
    ACC_ENUM(0x4000),
    ACC_MODULE(0x8000);


    public final int value;

    AccessFlagEnum(int value) {
        this.value = value;
    }
}
