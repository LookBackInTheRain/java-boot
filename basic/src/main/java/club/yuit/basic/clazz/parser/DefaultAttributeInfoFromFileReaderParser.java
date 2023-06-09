package club.yuit.basic.clazz.parser;

import club.yuit.basic.clazz.annotations.Lexer;
import club.yuit.basic.clazz.parser.attr.AttrParserManager;
import club.yuit.basic.clazz.struct.AttributeInfo;
import club.yuit.basic.clazz.struct.AttributeItem;
import club.yuit.basic.clazz.struct.Struct;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuit
 * @date 2023/6/6
 **/

@Lexer(
        order = 6
)
public class DefaultAttributeInfoFromFileReaderParser extends AbstractAttributeFormFileReaderParser {


    @Override
    public void doParser(Reader reader, Struct struct) {
        struct.setAttributeInfo(doAttrParser(reader, struct));
    }


}
