package club.yuit.basic.clazz.struct;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yuit
 * @date 2023/5/31
 *
 * this_class 类索引，当前类的全限定名称（包名+文件名）
 *
 **/
@Getter
@Setter
public class ClassOrInterfaceIndex {

    /**
     * u2 两字节,常量池CONSTANT_Class_info索引
     */
    private int index;

    public ClassOrInterfaceIndex(int index) {
        this.index = index;
    }

    public String getValue(Struct struct){
       return struct.getCpInfo().get(this.index).getValue();
    }


}
