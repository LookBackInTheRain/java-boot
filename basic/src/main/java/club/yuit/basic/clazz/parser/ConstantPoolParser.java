package club.yuit.basic.clazz.parser;

import club.yuit.basic.clazz.annotations.Lexer;
import club.yuit.basic.clazz.constantpool.ConstantPoolParserManger;
import club.yuit.basic.clazz.struct.ConstantPool;
import club.yuit.basic.clazz.struct.Struct;

import java.io.IOException;

/**
 * @author yuit
 * @date 2023/6/1
 **/


@Lexer(order = 1)
public class ConstantPoolParser   extends AbstractParser{




    @Override
    public void doParser(Reader reader, Struct struct) {
        ConstantPool pool = new ConstantPool();
        struct.setConstantPool(pool);
        int poolCount = reader.readU2();
        pool.setCount(poolCount);

        ConstantPoolParserManger m = new ConstantPoolParserManger(reader, pool);
        for (int i=0;i<poolCount-1;i++){
            int tag = reader.readU1();
            try {
                m.parse(tag);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
