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
public class ConstantValue  extends AttributeItem {

    /**
     * u2 常量池中一个字面量常量的引用，根据字段类型的不同，
     * 字面 量可以是CONSTANT_Long_info、CONSTANT_Float_info、CONSTANT_Double_info、
     * CONSTANT_Integer_info和CONSTANT_String_info常量中的一种
     */
    private int cvIndex;
}
