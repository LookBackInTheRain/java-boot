package club.yuit.basic.clazz.constantpool.parser;

import club.yuit.basic.clazz.Struct;
import club.yuit.basic.clazz.annotations.ConstPoolLexer;
import club.yuit.basic.clazz.parser.AbstractParser;

import java.io.InputStream;

/**
 * @author yuit
 * @date 2022/5/24
 **/
public abstract class AbstractConstantInfo extends AbstractParser  {



   private Struct struct;

    public AbstractConstantInfo(Struct struct) {
        super(struct);
    }

}
