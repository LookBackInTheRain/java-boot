package club.yuit.basic.clazz.parser;

import club.yuit.basic.clazz.constantpool.ConstantPoolParserManger;
import club.yuit.basic.clazz.struct.Struct;

import java.io.IOException;

/**
 * @author yuit
 * @date 2023/6/1
 **/


public class ConstantPoolParser   extends AbstractParser{




    @Override
    public void doParser(Reader reader, Struct struct) {
        int poolCount = reader.readByte();
        ConstantPoolParserManger m = new ConstantPoolParserManger(reader,struct);
        for (int i=0;i<poolCount-1;i++){
            int tag = reader.readByte();
            try {
                m.parse(tag);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
