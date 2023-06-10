package club.yuit.basic.clazz.parser;

import club.yuit.basic.clazz.constantpool.parser.AbstractConstantInfo;
import club.yuit.basic.clazz.parser.attr.AttrParserManager;
import club.yuit.basic.clazz.struct.AttributeInfo;
import club.yuit.basic.clazz.struct.AttributeItem;
import club.yuit.basic.clazz.struct.Struct;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuit
 * @date 2023/6/9
 **/
public abstract class AbstractAttributeFormFileReaderParser  extends AbstractParser{


    protected AttributeInfo doAttrParser(Reader reader, Struct struct){
        AttributeInfo attributeInfo = new AttributeInfo();
        int attrCount = reader.readU2();
        List<AttributeItem> attributeItems = attributeInfo.getAttributeItems();
        if (attributeItems == null) {
            attributeItems = new ArrayList<>();
            attributeInfo.setAttributeItems(attributeItems);
        }

        if (attrCount>0){
            attributeInfo.setCount(attrCount);
            AttrParserManager manager = new AttrParserManager(struct);

            for (int i = 0; i < attrCount; i++) {
                AttributeItem item = new AttributeItem();
                item.setNameIndex(reader.readU2());
                int length = reader.readInt();
                item.setLength(length);
                if (length > 0) {
                    item.setBuffer(reader.readBuffer(length));
                }
                item = manager.doParser(item);
                attributeItems.add(item);
            }
        }

        return attributeInfo;
    }

}
