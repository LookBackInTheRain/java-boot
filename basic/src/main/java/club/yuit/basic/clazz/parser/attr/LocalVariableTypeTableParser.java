package club.yuit.basic.clazz.parser.attr;

import club.yuit.basic.clazz.annotations.AttrLexer;
import club.yuit.basic.clazz.constantpool.parser.AbstractConstantInfo;
import club.yuit.basic.clazz.struct.AttributeItem;
import club.yuit.basic.clazz.struct.Struct;
import club.yuit.basic.clazz.struct.attr.LocalVariableTable;
import club.yuit.basic.clazz.struct.attr.LocalVariableTableInfo;
import club.yuit.basic.clazz.struct.attr.LocalVariableTypeTable;
import club.yuit.basic.clazz.struct.attr.LocalVariableTypeTableInfo;
import club.yuit.basic.clazz.utils.ByteBufferReader;

/**
 * @author: yuit
 * @date: 2023/06/10 13:12
 */
@AttrLexer("LocalVariableTypeTable")
public class LocalVariableTypeTableParser extends AttrItemParser {
    @Override
    public AttributeItem doParser(AttributeItem source, AttrParserManager manager) {

        LocalVariableTypeTable table = new LocalVariableTypeTable(source);
        ByteBufferReader reader = table.getReader();
        Struct struct = manager.struct;
        int tableLength = reader.readU2();
        table.setTableLength(tableLength);
        if (tableLength > 0) {
            LocalVariableTypeTableInfo[] tableInfos = new LocalVariableTypeTableInfo[tableLength];
            for (int i = 0; i < tableLength; i++) {
                LocalVariableTypeTableInfo info = new LocalVariableTypeTableInfo();

                info.setStartPc(reader.readU2());
                info.setLength(reader.readU2());
                info.setNameIndex(reader.readU2());
                info.setSignatureIndex(reader.readU2());
                info.setIndex(reader.readU2());
                AbstractConstantInfo nameInfo = struct.getConstantPoolInfo(info.getNameIndex());
                AbstractConstantInfo signatureInfo = struct.getConstantPoolInfo(info.getSignatureIndex());
                info.setName(nameInfo.getValue());
                info.setSignature(signatureInfo.getValue());

                tableInfos[i] = info;
            }

            table.setVariableTableInfos(tableInfos);
        }

        return table;
    }
}
