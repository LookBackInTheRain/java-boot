package club.yuit.basic.clazz.struct.attr;

import club.yuit.basic.clazz.struct.AttributeItem;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: yuit
 * @date: 2023/06/10 14:14
 * 记录泛型类型
 */
@Getter
@Setter
public class Signature  extends AttributeItem {

    /**
     * u2  对常量池的有效索引。常量池在该索引处的项必须是
     * CONSTANT_Utf8_info结构，表示类签名或方法类型签名或字段类型签名
     */
    private int signatureIndex;

    public Signature(AttributeItem source) {
        source.copy(this);
    }

    public Signature() {
    }
}
