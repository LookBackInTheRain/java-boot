package club.yuit.basic.clazz.parser.attr;

import club.yuit.basic.clazz.annotations.AttrLexer;
import club.yuit.basic.clazz.struct.AttributeItem;
import club.yuit.basic.clazz.struct.attr.InnerClasses;
import club.yuit.basic.clazz.struct.attr.InnerClassesInfo;
import club.yuit.basic.clazz.utils.ByteBufferReader;

/**
 * @author yuit
 * @date 2023/6/7
 * 内部类属性
 **/
@AttrLexer("InnerClasses")
public class InnerClassParser  extends AttrItemParser{
    @Override
    public AttributeItem doParser(AttributeItem source, AttrParserManager manager) {

        InnerClasses innerClass = new InnerClasses();
        source.copy(innerClass);
        ByteBufferReader reader = innerClass.getReader();
        int numberOfClasses = reader.readU2();
        innerClass.setNumberOfClasses(numberOfClasses);
        if (numberOfClasses>0){
            InnerClassesInfo[] innerClassInfos = new InnerClassesInfo[numberOfClasses];
            for (int i=0;i<numberOfClasses;i++){
                InnerClassesInfo info = new InnerClassesInfo();
                info.setIciIndex(reader.readU2());
                info.setOciIndex(reader.readU2());
                info.setInIndex(reader.readU2());
                info.setIcaFlags(reader.readU2());
                innerClassInfos[i]=info;
            }
            innerClass.setInnerClassInfos(innerClassInfos);
        }
        return innerClass;
    }
}
