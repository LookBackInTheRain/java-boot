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
public class AttributeInfoFromFileReaderParser extends AbstractParser {


    @Override
    public void doParser(Reader reader, Struct struct) {
        int attrCount = reader.readU2();
        AttributeInfo attributeInfo = struct.getAttributeInfo();
        List<AttributeItem> attributeItems = attributeInfo.getAttributeItems();
        if (attributeItems == null) {
            attributeItems = new ArrayList<>();
            attributeInfo.setAttributeItems(attributeItems);
        }

        if (attrCount>0){

            AttrParserManager manager = new AttrParserManager(struct.getConstantPool().getCpInfo());

            for (int i = 0; i < attrCount; i++) {
                AttributeItem item = new AttributeItem();
                item.setNameIndex(reader.readU2());
                int length = reader.readU2();
                item.setLength(length);
                if (length > 0) {
                    item.setBuffer(reader.readBuffer(length));
                }
                item = manager.doParser(item);
                attributeItems.add(item);
            }
        }

    }




}
