package club.yuit.basic.clazz.constantpool.parser;

import club.yuit.basic.clazz.annotations.AttrLexer;
import club.yuit.basic.clazz.parser.AbstractParser;
import club.yuit.basic.clazz.parser.Reader;
import club.yuit.basic.clazz.parser.attr.AttrItemParser;
import club.yuit.basic.clazz.parser.attr.AttrParserManager;
import club.yuit.basic.clazz.parser.attr.SourceDebugExtension;
import club.yuit.basic.clazz.struct.AttributeItem;
import club.yuit.basic.clazz.struct.Struct;
import club.yuit.basic.clazz.utils.ByteBufferReader;

import java.nio.charset.StandardCharsets;

/**
 * @author: yuit
 * @date: 2023/06/10 15:50
 */
@AttrLexer("SourceDebugExtension")
public class SourceDebugExtensionParser extends AttrItemParser {


    @Override
    public AttributeItem doParser(AttributeItem source, AttrParserManager manager) {
        SourceDebugExtension extension = new SourceDebugExtension(source);
        ByteBufferReader reader = extension.getReader();
        byte[] bytes = reader.readBytes(extension.getLength());
        extension.setData(bytes);
        extension.setValue(new String(bytes, StandardCharsets.UTF_8));
        return extension;
    }
}
