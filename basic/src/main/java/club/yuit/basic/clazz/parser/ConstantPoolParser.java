package club.yuit.basic.clazz.parser;

import club.yuit.basic.clazz.Struct;
import club.yuit.basic.clazz.constantpool.ConstantPoolParserManger;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author yuit
 * @date 2023/6/1
 **/


public class ConstantPoolParser   extends AbstractParser{

    public ConstantPoolParser(InputStream stream) {
        super(stream, struct);
    }


    @Override
    public void doParser(Struct struct) {
        int poolCount = readByte();
        ConstantPoolParserManger m = new ConstantPoolParserManger(struct);
        for (int i=0;i<poolCount-1;i++){
            int tag = readByte();
            try {
                m.parse(tag,getStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
