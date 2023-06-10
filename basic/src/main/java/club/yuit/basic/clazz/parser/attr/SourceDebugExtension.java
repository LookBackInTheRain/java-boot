package club.yuit.basic.clazz.parser.attr;

import club.yuit.basic.clazz.struct.AttributeItem;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: yuit
 * @date: 2023/06/10 15:49
 */
@Getter
@Setter
public class SourceDebugExtension  extends AttributeItem {

    private byte[] data;
    private String value;

    public SourceDebugExtension(AttributeItem source) {
        source.copy(this);
    }

    public SourceDebugExtension() {
    }
}
