package club.yuit.basic.clazz.parser.attr;

import club.yuit.basic.clazz.annotations.AttrLexer;
import club.yuit.basic.clazz.annotations.ConstPoolLexer;
import club.yuit.basic.clazz.constantpool.parser.AbstractConstantInfo;
import club.yuit.basic.clazz.parser.attr.AttrItemParser;
import club.yuit.basic.clazz.struct.AttributeItem;
import cn.hutool.core.util.ClassUtil;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author yuit
 * @date 2023/6/6
 **/
public class AttrParserManager  {


    final static Map<String, AttrItemParser> PARSER_MAP = new HashMap<>();

    private final List<AbstractConstantInfo> pool;


    public AttrParserManager(List<AbstractConstantInfo> pool) {
        this.pool = pool;
        loadParserClassAndInitInstance();
    }

    private void  loadParserClassAndInitInstance(){
        Set<Class<?>> classes = ClassUtil.scanPackage("club.yuit.basic.clazz.parser.attr",
                aClass -> aClass.getAnnotation(AttrLexer.class) != null);
        classes.forEach(i->{
            AttrLexer annotation = i.getAnnotation(AttrLexer.class);
            PARSER_MAP.put(annotation.value(),newInstance(i));
        });
    }



   public AttributeItem doParser(AttributeItem source) {
        int nameIndex = source.getNameIndex();
        String value = pool.get(nameIndex - 1).getValue();
        AttrItemParser itemParser = PARSER_MAP.get(value);
        if (itemParser!=null&&source.getBuffer()!=null){
            return itemParser.doParser(source,this);
        }
        return source;
    }

    private AttrItemParser newInstance(Class<?> cls){
        try {
            Constructor<?> constructor = cls.getConstructor();
            return (AttrItemParser) constructor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
