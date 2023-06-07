package club.yuit.basic.clazz.struct.attr;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yuit
 * @date 2023/6/7
 **/
@Getter
@Setter
public class InnerClassInfo {
    /**
     * u2 inner_class_info_index
     * 是指向常量池中CONSTANT_Class_info型常量的索 引，分别代表了内部类的符号引用
     */
    private int iciIndex;
    /**
     * u2 outer_class_info_class
     * 是指向常量池中CONSTANT_Class_info型常量的索 引，宿主类的符号引用
     */
    private int ociIndex;


    /**
     * u2 inner_name_index
     * 指向常量池中CONSTANT_Utf8_info型常量的索引，代表这个内部类的名称， 如果是匿名内部类，这项值为0
     */
    private int inIndex;


    /**
     * u2 inner_class_access_flags
     * 内部类的访问标志，类似于类的access_flags
     */
    private int icaFlags;
}
