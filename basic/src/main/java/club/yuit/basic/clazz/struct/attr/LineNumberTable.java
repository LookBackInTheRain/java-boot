package club.yuit.basic.clazz.struct.attr;

import club.yuit.basic.clazz.struct.AttributeItem;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yuit
 * @date 2023/6/7
 **/
@Setter
@Getter
public class LineNumberTable  extends AttributeItem {

    /**
     * u2 line_table 容量
     */
    private int lnTableLength;

    private LineNumberInfo[] lnTable;

}
