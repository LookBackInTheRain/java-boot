package club.yuit.basic.clazz.parser.attr;

import club.yuit.basic.clazz.annotations.AttrLexer;
import club.yuit.basic.clazz.constantpool.parser.AbstractConstantInfo;
import club.yuit.basic.clazz.struct.AttributeItem;
import club.yuit.basic.clazz.struct.Struct;
import club.yuit.basic.clazz.struct.attr.SourceFile;
import club.yuit.basic.clazz.utils.ByteBufferReader;

/**
 * @author: yuit
 * @date: 2023/06/10 14:29
 */
@AttrLexer("SourceFile")
public class SourceFileParser  extends AttrItemParser{
    @Override
    public AttributeItem doParser(AttributeItem source, AttrParserManager manager) {
        SourceFile sourceFile = new SourceFile(source);
        ByteBufferReader reader = sourceFile.getReader();
        int sfIndex = reader.readU2();
        sourceFile.setSfIdnex(sfIndex);
        Struct struct = manager.struct;

        AbstractConstantInfo constantPoolInfo = struct.getConstantPoolInfo(sfIndex);
        sourceFile.setSourceName(constantPoolInfo.getValue());
        return sourceFile;
    }
}
