package club.yuit.basic.clazz.struct;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yuit
 * @date 2023/6/9
 **/

@Getter
@Setter
public class FieldMethodInfoItem {
    private int accFlagsIntValue;
    private int nameIndex;
    private int descriptorIndex;
    private AttributeInfo attributeInfo;
}
