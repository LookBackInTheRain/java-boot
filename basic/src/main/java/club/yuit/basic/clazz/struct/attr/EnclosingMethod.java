package club.yuit.basic.clazz.struct.attr;

import club.yuit.basic.clazz.struct.AttributeItem;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yuit
 * @date 2023/6/7
 * 当前类是局部内部类或匿名类时，必须具有EnclosingMethod属性
 * 用于表示这个类所在的外围方法
 **/
@Getter
@Setter
public class EnclosingMethod extends AttributeItem {
    /**
     * u2 常量池索引且索引的类型必须是CONSTANT_Class_info
     * 表示内部类封装当前类的声明
     *  representing the innermost class that encloses the declaration of the current class
     */
    private int classIndex;

    /**
     * u2 todo 还需要后续解析
     */
    private int methodIndex;
}
