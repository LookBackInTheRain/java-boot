package club.yuit.basic.clazz.parser.attr;

import club.yuit.basic.clazz.annotations.AttrLexer;
import club.yuit.basic.clazz.struct.AttributeItem;
import club.yuit.basic.clazz.struct.attr.StackMapTable;

/**
 * @author yuit
 * @date 2023/6/7
 * todo 未完成解析
 **/

@AttrLexer("StackMapTable")
public class StackMapTableParser  extends AttrItemParser{
    @Override
    public AttributeItem doParser(AttributeItem source, AttrParserManager manager) {
        StackMapTable table = new StackMapTable();
        source.copy(table);
        return table;
    }
}
