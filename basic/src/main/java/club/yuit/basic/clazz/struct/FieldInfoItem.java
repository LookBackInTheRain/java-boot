package club.yuit.basic.clazz.struct;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yuit
 * @date 2023/6/6
 **/

@Getter
@Setter
public class FieldInfoItem {

    private int accFlagsIntValue;
    private int nameIndex;
    private int descriptorIndex;

    private AttributeInfo attributeInfo;

}
