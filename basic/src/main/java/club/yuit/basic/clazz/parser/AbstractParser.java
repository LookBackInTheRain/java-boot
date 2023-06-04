package club.yuit.basic.clazz.parser;

import club.yuit.basic.clazz.annotations.Lexer;
import club.yuit.basic.clazz.struct.ClassFile;
import club.yuit.basic.clazz.struct.Struct;

import java.io.InputStream;

/**
 * @author yuit
 * @date 2023/5/31
 **/
public abstract class AbstractParser {





    public abstract void doParser(Reader reader,Struct struct);

    public int order(){
        return this.getClass().getAnnotation(Lexer.class).order();
    }

}
