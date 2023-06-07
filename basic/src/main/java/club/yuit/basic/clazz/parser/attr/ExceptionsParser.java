package club.yuit.basic.clazz.parser.attr;

import club.yuit.basic.clazz.annotations.AttrLexer;
import club.yuit.basic.clazz.struct.AttributeItem;
import club.yuit.basic.clazz.struct.attr.Exceptions;
import club.yuit.basic.clazz.utils.ByteBufferReader;

/**
 * @author yuit
 * @date 2023/6/7
 **/
@AttrLexer("Exceptions")
public class ExceptionsParser  extends AttrItemParser{
    @Override
    public AttributeItem doParser(AttributeItem source, AttrParserManager manager) {
        Exceptions exceptions = new Exceptions();
        source.copy(exceptions);
        ByteBufferReader reader = exceptions.getReader();

        int numOfExps = reader.readU2();
        exceptions.setNumberOfExceptions(numOfExps);
        if (numOfExps>0){
            int[] expIndex = new int[numOfExps];
            for (int i=0;i<numOfExps;i++){
                expIndex[i]= (reader.readU2()-1);
            }
            exceptions.setExpIndexTable(expIndex);
        }

        return exceptions;
    }
}
