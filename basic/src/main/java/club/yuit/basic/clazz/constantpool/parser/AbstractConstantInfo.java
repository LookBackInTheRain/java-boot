package club.yuit.basic.clazz.constantpool.parser;

import club.yuit.basic.clazz.annotations.ConstPoolParser;

import java.util.List;

/**
 * @author yuit
 * @date 2022/5/24
 **/
public abstract class AbstractConstantInfo implements ConstantInfo {


    private List<ConstantInfo> pools;

    public AbstractConstantInfo(List<ConstantInfo> pools){
        this.pools = pools;
    }



    public List<ConstantInfo> getPools() {
        return pools;
    }


    public boolean access(int tag) {
        ConstPoolParser annotation = this.getClass().getAnnotation(ConstPoolParser.class);
        return annotation!=null && annotation.value()==tag;
    }
}
