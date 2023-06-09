package club.yuit.basic.clazz.parser;

import club.yuit.basic.clazz.annotations.Lexer;
import club.yuit.basic.clazz.struct.*;

/**
 * @author yuit
 * @date 2023/6/9
 * 解析字段或方法集合
 **/
public abstract class FieldMethodInfoParser extends AbstractAttributeFormFileReaderParser {


    protected FieldMethodInfo doFMParser(Reader reader, Struct struct){
        FieldMethodInfo fieldMethodInfo = new FieldMethodInfo();
        int count = reader.readU2();
        fieldMethodInfo.setCount(count);
        if (count>0){
            FieldMethodInfoItem[] items =new FieldMethodInfoItem[count];
            for (int i=0;i<count;i++){
                FieldMethodInfoItem item = new FieldMethodInfoItem();
                int accessFlags = reader.readU2();
                int nameIndex = reader.readU2();
                int descriptorIndex = reader.readU2();
                AttributeInfo info = doAttrParser(reader, struct);
                item.setAccFlagsIntValue(accessFlags);
                item.setNameIndex(nameIndex);
                item.setDescriptorIndex(descriptorIndex);
                item.setAttributeInfo(info);
                items[i] = item;
            }
            fieldMethodInfo.setItems(items);
        }

        return fieldMethodInfo;
    }
}
