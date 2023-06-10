package club.yuit.basic.clazz.struct.attr;

import club.yuit.basic.clazz.struct.AttributeItem;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: yuit
 * @date: 2023/06/10 14:26
 * 用于描述代码源文件
 */
@Getter
@Setter
public class SourceFile  extends AttributeItem {

    /**
     * u2 常量池索引CONSTANT_Utf8_info，表示源码文件名称
     */
    private int sfIdnex;
    private String sourceName;

    public SourceFile(AttributeItem source) {
        source.copy(this);
    }

    public SourceFile() {

    }
}
