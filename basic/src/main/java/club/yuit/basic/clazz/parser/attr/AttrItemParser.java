package club.yuit.basic.clazz.parser.attr;

import club.yuit.basic.clazz.annotations.AttrLexer;
import club.yuit.basic.clazz.struct.AttributeInfo;
import club.yuit.basic.clazz.struct.AttributeItem;
import club.yuit.basic.clazz.utils.ByteBufferReader;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuit
 * @date 2023/6/6
 **/
public abstract class AttrItemParser {


   abstract  AttributeItem doParser(AttributeItem source,AttrParserManager manager);


   protected String code(){
       AttrLexer annotation = this.getClass().getAnnotation(AttrLexer.class);
       if (annotation!=null){
           return annotation.value();
       }

       return null;
   }


    public static AttributeInfo attributeInfoFormItem(AttributeItem source, AttrParserManager manager) {
        ByteBufferReader reader = source.getReader();

        int attrCount = reader.readU2();
        AttributeInfo attributeInfo = new AttributeInfo();
        List<AttributeItem> items = new ArrayList<>();
        attributeInfo.setAttributeItems(items);

        for (int i = 0; i < attrCount; i++) {
            AttributeItem item = new AttributeItem();
            item.setNameIndex(reader.readU2());
            int length = reader.readU2();
            item.setLength(length);
            if (length > 0) {
                item.setBuffer(reader.readBuffer(length));
            }

            item = manager.doParser(item);

            items.add(item);
        }

        if (items.isEmpty()){
            return null;
        }

        return attributeInfo;

    }



}
