package club.yuit.basic.clazz.constantpool;

import club.yuit.basic.clazz.Struct;
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
public class ConstantPoolParserManger {

    private  List<ConstantInfo> list;
    private final static Map<Integer,Class<?>> PARSERS_CLASS = new HashMap<>(19);

    public ConstantPoolParserManger(Struct struct) {
        if (struct.getCpInfo()!=null){
            this.list=struct.getCpInfo();
        }else {
            this.list = new ArrayList<>();
            struct.setCpInfo(list);
        }
        loadParserClass("club.yuit.basic.clazz.constantpool.parser");
    }

    public void parse(int tag, InputStream in) throws IOException {
        ConstantInfo info = chose(tag);
        list.add(info);
        info.handle(in);
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

    ConstantInfo chose(int tag)  {
        Class<?> clas = PARSERS_CLASS.get(tag);
        if (clas==null){
            return null;
        }
        Constructor<?> constructor = null;
        try {
            constructor= clas.getConstructor(List.class);
            Object o = constructor.newInstance(list);
            return (ConstantInfo) o;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }



}
