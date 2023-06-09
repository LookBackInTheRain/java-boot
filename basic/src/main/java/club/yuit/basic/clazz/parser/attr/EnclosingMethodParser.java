package club.yuit.basic.clazz.parser.attr;

import club.yuit.basic.clazz.annotations.AttrLexer;
import club.yuit.basic.clazz.struct.AttributeItem;
import club.yuit.basic.clazz.struct.attr.EnclosingMethod;
import club.yuit.basic.clazz.utils.ByteBufferReader;


/**
 * @author yuit
 * @date 2023/6/9
 **/
@AttrLexer("EnclosingMethod")
public class EnclosingMethodParser  extends AttrItemParser{
    @Override
    public AttributeItem doParser(AttributeItem source, AttrParserManager manager) {
        EnclosingMethod method = new EnclosingMethod();
        source.copy(method);
        ByteBufferReader reader = method.getReader();
        method.setClassIndex(reader.readU2());
        method.setMethodIndex(reader.readU2());
        return method;
    }
}
