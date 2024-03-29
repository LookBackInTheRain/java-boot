package club.yuit.basic.clazz.constantpool;

import club.yuit.basic.clazz.constantpool.parser.AbstractConstantInfo;
import club.yuit.basic.clazz.parser.Reader;
import club.yuit.basic.clazz.struct.ConstantPool;
import club.yuit.basic.clazz.struct.Struct;
import club.yuit.basic.clazz.annotations.ConstPoolLexer;
import club.yuit.basic.clazz.constantpool.parser.ConstantInfo;
import cn.hutool.core.util.ClassUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.*;

/**
 * @author yuit
 * @date 2022/5/23
 **/
@Slf4j
public class ConstantPoolParserManger  {

    Reader reader;
    List<AbstractConstantInfo> pool;
    ConstantPool constantPool;
    private final static Map<Integer,Class<?>> PARSERS_CLASS = new HashMap<>(19);

    public ConstantPoolParserManger(Reader reader,ConstantPool constantPool) {
        this.reader = reader;
        this.constantPool = constantPool;
        if (constantPool.getCpInfo()!=null){
            this.pool=constantPool.getCpInfo();
        }else {
            this.pool = new ArrayList<>();
            constantPool.setCpInfo(pool);
        }
        loadParserClass("club.yuit.basic.clazz.constantpool.parser");
    }

    public void parse(int tag) throws IOException {

        AbstractConstantInfo info = chose(tag);
        pool.add(info);
        log.debug("tag is {}，parser type is {}",tag,info.getClass().getName());
        info.doParser(reader);
    }

    public void loadParserClass(String pkg){
        log.info("loading parser classes");
        Set<Class<?>> classes = ClassUtil.scanPackage(pkg, aClass -> aClass.getAnnotation(ConstPoolLexer.class) != null);
        classes.forEach(i->{
            ConstPoolLexer annotation = i.getAnnotation(ConstPoolLexer.class);
            PARSERS_CLASS.put(annotation.value(),i);
        });
        log.info("loaded parser classes,size:{}",classes.size());
    }

    AbstractConstantInfo chose(int tag)  {
        Class<?> clas = PARSERS_CLASS.get(tag);
        if (clas==null){
            return null;
        }
        Constructor<?> constructor = null;
        try {
            constructor= clas.getConstructor(List.class);
            Object o = constructor.newInstance(pool);
            return (AbstractConstantInfo) o;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }



}
