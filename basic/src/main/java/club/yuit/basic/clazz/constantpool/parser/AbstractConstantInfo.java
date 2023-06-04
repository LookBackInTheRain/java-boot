package club.yuit.basic.clazz.constantpool.parser;

import club.yuit.basic.clazz.annotations.ConstPoolLexer;
import club.yuit.basic.clazz.parser.Reader;
import club.yuit.basic.clazz.struct.Struct;
import club.yuit.basic.clazz.parser.AbstractParser;

import java.nio.channels.IllegalSelectorException;
import java.util.List;

/**
 * @author yuit
 * @date 2022/5/24
 **/
public abstract class AbstractConstantInfo   {


    protected final List<AbstractConstantInfo> pool;

    public AbstractConstantInfo(List<AbstractConstantInfo> pool) {
       this.pool = pool;
    }

   public abstract void doParser(Reader reader);


    public abstract void print();

    public abstract String getValue();


    public int getTag(){
        ConstPoolLexer annotation = this.getClass().getAnnotation(ConstPoolLexer.class);
        if (annotation==null){
            throw new IllegalSelectorException();
        }

        return annotation.value();
    }



}
