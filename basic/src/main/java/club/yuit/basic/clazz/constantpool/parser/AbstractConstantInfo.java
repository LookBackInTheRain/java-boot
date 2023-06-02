package club.yuit.basic.clazz.constantpool.parser;

import club.yuit.basic.clazz.parser.Reader;
import club.yuit.basic.clazz.struct.Struct;
import club.yuit.basic.clazz.parser.AbstractParser;

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




}
