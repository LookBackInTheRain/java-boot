package club.yuit.basic.clazz.parser.attr;

import club.yuit.basic.clazz.annotations.AttrLexer;
import club.yuit.basic.clazz.struct.AttributeItem;
import club.yuit.basic.clazz.struct.attr.LineNumberInfo;
import club.yuit.basic.clazz.struct.attr.LineNumberTable;
import club.yuit.basic.clazz.utils.ByteBufferReader;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yuit
 * @date 2023/6/7
 **/
@AttrLexer("LineNumberTable")
public class LineNumberTableParser extends AttrItemParser{
    @Override
    public AttributeItem doParser(AttributeItem source, AttrParserManager manager) {
        LineNumberTable table = new LineNumberTable();
        source.copy(table);

        ByteBufferReader reader = table.getReader();

        table.setLnTableLength(reader.readU2());

        if (table.getLnTableLength()>0){
            LineNumberInfo[] infos = new LineNumberInfo[table.getLnTableLength()];
            for (int i=0;i<table.getLnTableLength();i++){
                LineNumberInfo info = new LineNumberInfo();
                info.setStartPc(reader.readU2());
                info.setLineNumber(reader.readU2());
                infos[i] = info;
            }

            table.setLnTable(infos);
        }

        return table;

    }
}
