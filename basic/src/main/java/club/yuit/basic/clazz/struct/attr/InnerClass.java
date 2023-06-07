package club.yuit.basic.clazz.struct.attr;

import club.yuit.basic.clazz.struct.AttributeItem;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yuit
 * @date 2023/6/7
 **/
@Getter
@Setter
public class InnerClass extends AttributeItem {

    /**
     * u2 内部类数量
     */
    private int numberOfClasses;

    private InnerClassInfo[] innerClassInfos;

}
