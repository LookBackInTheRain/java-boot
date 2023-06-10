package club.yuit.basic.clazz.parser.attr;

import club.yuit.basic.clazz.annotations.AttrLexer;
import club.yuit.basic.clazz.struct.AttributeInfo;
import club.yuit.basic.clazz.struct.AttributeItem;
import club.yuit.basic.clazz.struct.attr.Code;
import club.yuit.basic.clazz.struct.attr.ExceptionInfo;
import club.yuit.basic.clazz.utils.ByteBufferReader;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yuit
 * @date 2023/6/6
 **/

@AttrLexer("Code")
public class CodeParser extends AttrItemParser {


    @Override
    public AttributeItem doParser(AttributeItem source,AttrParserManager manager) {
        ByteBuffer buffer = source.getBuffer();
        if (buffer==null){
            throw new IllegalArgumentException("content is null");
        }

        Code code = new Code();
        source.copy(code);

        ByteBufferReader reader = code.getReader();

        code.setMaxStack(reader.readU2());
        code.setMaxLocals(reader.readU2());
        code.setCodeLength(reader.readInt());
        code.setCodes(reader.readBytes(code.getCodeLength()));

        code.setExpTableLength(reader.readU2());

        // 解析异常表
        if (code.getExpTableLength()>0){
            List<ExceptionInfo> exceptionTable = new ArrayList<>(code.getExpTableLength());
            for (int i=0;i<code.getExpTableLength();i++){
                ExceptionInfo info = new ExceptionInfo();
                info.setStartPc(reader.readU2());
                info.setEndPc(reader.readU2());
                info.setHandlePc(reader.readU2());
                info.setCatchType(reader.readU2());
                exceptionTable.add(info);
            }

            code.setExceptionTable(exceptionTable);
        }

        // 属性表
        AttributeInfo info = attributeInfoFormItem(code, manager);
        code.setAttributeInfo(info);

        return code;

    }


}
