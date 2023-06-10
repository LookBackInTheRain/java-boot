package club.yuit.basic.clazz.parser.attr;

import club.yuit.basic.clazz.annotations.AttrLexer;
import club.yuit.basic.clazz.annotations.ConstPoolLexer;
import club.yuit.basic.clazz.constantpool.parser.AbstractConstantInfo;
import club.yuit.basic.clazz.parser.attr.AttrItemParser;
import club.yuit.basic.clazz.struct.AttributeItem;
import club.yuit.basic.clazz.struct.Struct;
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

    protected final List<AbstractConstantInfo> pool;
    protected final Struct struct;

    public AttrParserManager(Struct struct) {
        this.struct = struct;
        this.pool = struct.getConstantPool().getCpInfo();
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
        source.setName(value);
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
