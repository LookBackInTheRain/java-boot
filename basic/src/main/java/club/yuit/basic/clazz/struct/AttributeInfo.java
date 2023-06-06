package club.yuit.basic.clazz.struct;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author yuit
 * @date 2023/6/6
 **/
@Getter
@Setter
public class AttributeInfo {

    /**
     * 属性集合容量
     */
    private int count;
    private List<AttributeItem> attributeItems;

}
