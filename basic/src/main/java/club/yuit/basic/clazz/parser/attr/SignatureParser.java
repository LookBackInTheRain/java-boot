package club.yuit.basic.clazz.parser.attr;

import club.yuit.basic.clazz.annotations.AttrLexer;
import club.yuit.basic.clazz.struct.AttributeItem;
import club.yuit.basic.clazz.struct.attr.Signature;
import club.yuit.basic.clazz.utils.ByteBufferReader;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: yuit
 * @date: 2023/06/10 14:23
 */

@AttrLexer("Signature")
public class SignatureParser  extends AttrItemParser{
    @Override
    public AttributeItem doParser(AttributeItem source, AttrParserManager manager) {
        Signature  signature = new Signature(source);
        ByteBufferReader reader = signature.getReader();
        signature.setSignatureIndex(reader.readU2());
        return signature;
    }
}
