package club.yuit.basic.clazz.constantpool;

import club.yuit.basic.clazz.annotations.ConstPoolParser;
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

    public ConstantPoolParserManger(List<ConstantInfo> list) {
        this.list = list;
        loadParserClass("club.yuit.basic.clazz.constantpool.parser");
    }

    public void parse(int tag, InputStream in) throws IOException {
        ConstantInfo info = chose(tag);
        list.add(info);
        info.handle(in);
    }

    public void loadParserClass(String pkg){
        log.info("loading parser classes");
        Set<Class<?>> classes = ClassUtil.scanPackage(pkg, aClass -> aClass.getAnnotation(ConstPoolParser.class) != null);
        classes.forEach(i->{
            ConstPoolParser annotation = i.getAnnotation(ConstPoolParser.class);
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

    public static void main(String[] args) {
        List<ConstantInfo> l = new ArrayList<>();

        ConstantPoolParserManger m = new ConstantPoolParserManger(l);
        ConstantInfo chose = m.chose(8);
        System.out.println(chose);

    }


}
