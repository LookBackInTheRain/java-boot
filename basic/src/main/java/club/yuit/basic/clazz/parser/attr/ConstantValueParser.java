package club.yuit.basic.clazz.parser.attr;

import club.yuit.basic.clazz.annotations.AttrLexer;
import club.yuit.basic.clazz.struct.AttributeItem;
import club.yuit.basic.clazz.struct.attr.ConstantValue;

/**
 * @author yuit
 * @date 2023/6/7
 **/
@AttrLexer("ConstantValue")
public class ConstantValueParser  extends AttrItemParser{
    @Override
    AttributeItem doParser(AttributeItem source, AttrParserManager manager) {
        ConstantValue cv = new ConstantValue();
        source.copy(cv);
        cv.setCvIndex(cv.getReader().readU2());
        return cv;
    }
}
