package club.yuit.basic.clazz;

import club.yuit.basic.clazz.annotations.ConstPoolLexer;
import club.yuit.basic.clazz.annotations.Lexer;
import club.yuit.basic.clazz.constantpool.parser.AbstractConstantInfo;
import club.yuit.basic.clazz.parser.AbstractParser;
import club.yuit.basic.clazz.parser.ClassFileReader;
import club.yuit.basic.clazz.parser.Reader;
import club.yuit.basic.clazz.struct.ClassFile;
import club.yuit.basic.clazz.struct.Struct;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.util.*;

/**
 * @author yuit
 * @date 2023/5/31
 **/
@Slf4j
public class ClassFileParser {

    private static final List<AbstractParser> PARSERS = new ArrayList<>();


    private void loadParserClass(){

        log.info("loading classFile parser classes");
        Set<Class<?>> classes =
                ClassUtil.scanPackage("club.yuit.basic.clazz.parser",
                aClass -> aClass.getAnnotation(Lexer.class) != null);
        classes.forEach(i->{
           PARSERS.add(newInstance(i));
        });
        log.info("loaded classFile parser classes,size:{}",classes.size());

        PARSERS.sort(Comparator.comparingInt(AbstractParser::order));

    }

    public ClassFileParser() {
        loadParserClass();
    }

    public ClassFile parse(String filename){
        ClassFile classFile = ClassFile.build(filename);
        Reader reader = new ClassFileReader(classFile.getStream());

        PARSERS.forEach(i->{
            i.doParser(reader,classFile.getStruct());
        });

        return classFile;
    }

    static AbstractParser newInstance(Class<?> c)  {

        if (c==null){
            return null;
        }
        try {
            Constructor<?> constructor = c.getConstructor();
           return (AbstractParser) constructor.newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

}
