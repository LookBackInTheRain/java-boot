package club.yuit.basic.clazz.struct.attr;

import club.yuit.basic.clazz.struct.AttributeItem;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author yuit
 * @date 2023/6/7
 * 列举出方法中可能抛出的受查异常
 * 方法描述时在throws关键字后面列举的异常
 **/
@Getter
@Setter
public class Exceptions  extends AttributeItem {

    /**
     * u2 方法抛出多少种受检查异常
     */
    private int numberOfExceptions;

    /**
     * exception_index_table是一个指向常量池中 CONSTANT_Class_info型常量的索引，代表了该受查异常的类型
     * u2
     */
    private int[] expIndexTable;
}
